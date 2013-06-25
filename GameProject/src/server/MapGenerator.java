package server;

public class MapGenerator {

	public static TileContainer[][] generateNormalMap(int n, int m) {
		TileContainer[][] a = new TileContainer[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if ((i + j) % 5 == 0) {
					a[i][j] = new TileContainer(i, j, false);
				} else {
					a[i][j] = new TileContainer(i, j, true);
				}
			}
		}

		return a;
	}

}
