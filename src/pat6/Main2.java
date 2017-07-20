package pat6;

import java.util.*;

public class Main2 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int K = scan.nextInt();
		int M = scan.nextInt();
		int[] fullS = new int[K];
		for (int i = 0; i < K; i++)
			fullS[i] = scan.nextInt();
		grade[] g = new grade[N];
		for (int i = 0; i < N; i++)
			g[i] = new grade(i, K);
		for (int i = 0; i < M; i++) {
			int id = scan.nextInt();
			int t = scan.nextInt();
			int s = scan.nextInt();
			if (s != -1) {
				if (g[id - 1].nofs == 0)
					g[id - 1].sum = 0;
				g[id - 1].nofs++;
			}
			if (g[id - 1].s[t - 1] == -1 && s == -1)
				g[id - 1].s[t - 1] = 0;
			if (s > g[id - 1].s[t - 1]) {
				int cha = s;
				if (g[id - 1].s[t - 1] != -1)
					cha = s - g[id - 1].s[t - 1];
				g[id - 1].sum += cha;
				g[id - 1].s[t - 1] = s;
				if (g[id - 1].s[t - 1] == fullS[t - 1])
					g[id - 1].p++;
			}
		}

		Arrays.sort(g, new Comparator<grade>() {
			public int compare(grade g1, grade g2) {
				if (g1.sum != g2.sum)
					return g2.sum - g1.sum;
				if (g1.p != g2.p)
					return g2.p - g1.p;
				return g1.id.compareTo(g2.id);
			}
		});

		/*
		 * grade tmp = new grade(); for (int i = 0; i < N ; i++) { for (int j =
		 * i+1; j < N - i; j++) { if (g[i].sum < g[j].sum) { tmp = g[i]; g[i] =
		 * g[j]; g[j] = tmp; continue; } else if (g[i].p < g[j].p) { tmp = g[i];
		 * g[i] = g[j]; g[j] = tmp; continue; } else if
		 * (g[i].id.compareTo(g[j].id) < 0) { tmp = g[i]; g[i] = g[j]; g[j] =
		 * tmp; } } }
		 */

		/*
		 * for(int i=0;i<g.length;i++){ int k = i; for(int
		 * j=i+1;j<g.length;j++){ if(g[j].sum > g[k].sum){ k = j; }else
		 * if(g[j].sum == g[k].sum){ if(g[j].p > g[k].p){ k = j; }else if(g[j].p
		 * == g[k].p && g[j].id.compareTo(g[k].id)<0){ k = j; } } } grade tmp =
		 * g[k]; g[k] = g[i]; g[i] = tmp; }
		 */

		int r = 1, temp = g[0].sum;
		for (int i = 0; i < N; i++) {
			if (g[i].nofs < 1)
				continue;
			if (temp != g[i].sum) {
				r = i + 1;
				// r = N - i ;
				temp = g[i].sum;
			}
			System.out.print(r + " ");
			System.out.print(g[i].id + " " + g[i].sum);
			for (int j = 0; j < g[i].s.length; j++) {
				if (g[i].s[j] != -1) {
					System.out.print(" " + g[i].s[j]);
				} else {
					System.out.print(" -");
				}
			}
			System.out.println();
		}

		scan.close();
	}

	static class grade {
		String id;
		int[] s;
		int p = 0;
		int nofs = 0;
		int sum = -1;

		grade(int i, int K) {
			this.id = String.format("%05d", i + 1);
			s = new int[K];
			for (int j = 0; j < K; j++) {
				s[j] = -1;
			}
		}

		grade() {
		}
	}
}
