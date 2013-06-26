package server;

public class MapGenerator {

	public static TileContainer[][] generateNormalMap(int n, int m) {
		TileContainer[][] a = new TileContainer[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if ((i == 0 || j == 0 || i == n - 1 || j == m - 1)
						|| (i + j) % 5 == 0 && !spawnArea(i, j)) {
					a[i][j] = new TileContainer(i, j, false);
				} else {
					a[i][j] = new TileContainer(i, j, true);
				}
			}
		}

		return a;
	}

	private static boolean spawnArea(int i, int j) {
		return i >= 1 && j >= 1 && i <= 100 && j <= 100;
	}

}
