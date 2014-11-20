package com.romario.misoilab2.logic;

import com.romario.misoilab2.cluster.Cluster;
import com.romario.misoilab2.filter.FilterConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by romario on 11/20/14.
 */
public class KMeans {

	public void clustering(List<Cluster> clusters) {

		List<Cluster> oldCenters = new ArrayList<>();

		int countClasses = FilterConstant.COUNT_CLASSES;
		boolean flag = true;
		boolean[] checker = new boolean[countClasses];
		int[] centers = new int[countClasses];

		for (int i = 0; i < countClasses; i++) {
			Cluster cluster = clusters.get(i);
			cluster.setCenter(true);
			cluster.setBelongCluster(i);
			oldCenters.add(cluster);
		}


		for (Cluster cluster : clusters) {
			int[] distances = new int[countClasses];
			int count = 0;
			for (Cluster center : oldCenters) {
				if (cluster.isCenter()) {
					break;
				} else {
					distances[count] =
							distance(center.getArea(), cluster.getArea(), center.getPerimeter(),
									cluster.getPerimeter(),
									center.getDensity(), cluster.getDensity(), center.getCenterOfMass().x,
									cluster.getCenterOfMass().x,
									center.getCenterOfMass().y, cluster.getCenterOfMass().y);
					count++;
				}
			}
			if (!cluster.isCenter()) {
				cluster.setBelongCluster(checkNumberCluster(distances, countClasses));
			}
		}

		while (flag) {

			for (int i = 0; i < countClasses; i++) {
				List<Cluster> indexClusters = groupClusters(clusters, i);
				int oldCenter = centers[i];//findCenter(indexClusters);
				Cluster centerCluster = clusters.get(oldCenter);
				int[] distances = new int[indexClusters.size()];
				int count = 0;
				for (Cluster cluster : indexClusters) {

						if (cluster.isCenter()) {
							continue;
						} else {
							distances[count] =
									distance(centerCluster.getArea(), cluster.getArea(), centerCluster.getPerimeter(),
											cluster.getPerimeter(),
											centerCluster.getDensity(), cluster.getDensity(),
											centerCluster.getCenterOfMass().x,
											cluster.getCenterOfMass().x,
											centerCluster.getCenterOfMass().y, cluster.getCenterOfMass().y);
							count++;
						}
				}
				int newCenter = findNewCenter(distances);
				newCenter = findIndex(clusters, indexClusters.get(newCenter).getNumberCluster());
				if (oldCenter != newCenter) {
					clusters.get(oldCenter).setCenter(false);
					clusters.get(newCenter).setCenter(true);
					centers[i] = newCenter;
					checker[i] = false;
				} else {
					checker[i] = true;
				}



				//cluster.setBelongCluster(checkNumberCluster(distances, countClasses));
			}

			//findNewCenters(clusters);
			//List<Cluster> newCenters = createNewCenters(clusters);
			//flag = checkCenters(oldCenters, newCenters);

			flag = checkChecker(checker);


		}


	}

	private int findIndex(List<Cluster> clusters, int xIndex) {
		int index = 0;
		for (Cluster cluster : clusters) {
			if (cluster.getNumberCluster() == xIndex) {
				return index;
			}
			index++;
		}
		return index;
	}

	private boolean checkChecker(boolean[] flags) {
		for (int i = 0; i < flags.length; i++) {
			if (flags[i] == false) {
				return true;
			}
		}
		return false;
	}

	private int findNewCenter(int[] distances) {
		int index = distances[0];
		int res = 0;
		for (int i = 0; i < distances.length - 1; i++) {
			if (index < distances[i+1]) {
				index = distances[i];
				res = i;
			} else {
				index = distances[i+1];
				res = i;
			}
		}

		return res;
	}

	private int findCenter(List<Cluster> clusters) {
		int index = 0;
		for (Cluster cluster : clusters) {
			if (cluster.isCenter()) {
				return index;
			}
			index++;
		}
		System.out.println("ERROR KMeans");
		return index;

	}

	private List<Cluster> groupClusters(List<Cluster> clusters, int numberCluster) {
		List<Cluster> tmpList = new ArrayList<>();
		for (Cluster cluster : clusters) {
			if (cluster.getBelongCluster() == numberCluster) {
				tmpList.add(cluster);
			}
		}

		return tmpList;
	}

	/*private List<Cluster> findNewCenters(List<Cluster> clusters) {

	}*/

	private boolean checkCenters(List<Cluster> oldCenters, List<Cluster> newCenters) {
		boolean result = false;

		for (int i = 0; i < oldCenters.size(); i++) {
			if (oldCenters.get(i).getNumberCluster() == newCenters.get(i).getNumberCluster()) {
				result = true;
			} else {
				return false;
			}
		}

		return result;
	}

	private List<Cluster> createNewCenters(List<Cluster> clusters) {
		List<Cluster> tmpCenters = new ArrayList<>();

		for (Cluster cluster : clusters) {
			if (cluster.isCenter()) {
				tmpCenters.add(cluster);
			}
		}

		return tmpCenters;
	}

	private int checkNumberCluster(int[] distances, int countClasses) {
		int res = distances[0];
		int index = 0;
		for (int i = 0; i < countClasses - 1; i++) {
			if (res < distances[i+1] ) {
				res = distances[i];
				index = i;
			} else {
				res = distances[i+1];
				index = i+1;
			}
		}

		return index;
	}

  private int distance(int x, int x1, int y, int y1, int k, int k1, int zx, int zx1, int zy, int zy1) {
    int result = 0;

    result =
        (int) Math.sqrt( Math.pow((x - x1), 2) + Math.pow((y - y1), 2) + Math.pow((k - k1), 2)
            + Math.pow((zx  - zx1), 2) + Math.pow((zy - zy1), 2));

    return result;
  }
}
