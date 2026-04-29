//
// HX-2026-04-21: 50 points
//
// Please see lectures/lecture-04-21 for an
// example using DFirstEnumerate/BFirstEnumerate
//
// Some "hard" Sudoku puzzles can be
// found here: https://sudoku.com/hard/.
// You are asked to use DFirstEnumerate and BFirstEnumerate
// in FnGtree to solve Sudoku puzzles. Your solution should
// be able to solve "hard" Sudoku puzzles effectively.
//
// HIGH-LEVEL STRATEGY
//
//   A Sudoku puzzle is a 9x9 grid of digits 0..9, where 0 marks
//   an empty cell. Each Sudoku object exposes itself as an
//   FnGtree<Sudoku>:
//
//     - value() is just the Sudoku object itself (so the stream
//       enumerated by FnGtreeSUtil yields Sudoku snapshots).
//
//     - children() picks ONE empty cell to fill. To make the
//       search effective on "hard" puzzles, the cell chosen is
//       the empty cell with the fewest still-legal digits, the
//       classic Minimum Remaining Values (MRV) heuristic. For
//       that cell we generate one child Sudoku per legal digit;
//       impossible cells (zero candidates) and impossible whole
//       puzzles are simply represented by an empty children list,
//       which prunes the branch automatically.
//
//   To solve a puzzle:
//     - Soduku_dfs_solve calls FnGtreeSUtil.DFirstEnumerate(root)
//     - Soduku_bfs_solve calls FnGtreeSUtil.BFirstEnumerate(root)
//   Both produce a stream of Sudoku snapshots reachable from the
//   root by repeatedly filling an empty cell with a legal digit.
//   We then keep only snapshots that contain no empty cells (a
//   completed grid is a solution because every move keeps Sudoku
//   constraints intact). The result is an LnStrm<Sudoku> of all
//   solutions.
//
import Library00.FnList.*;
import Library00.LnStrm.*;
import Library00.FnGtree.*;

class Sudoku implements FnGtree<Sudoku> {
    int[][] grid;

    public Sudoku(int[][] g) {
	this.grid = new int[9][9];
	for (int r = 0; r < 9; r += 1) {
	    for (int c = 0; c < 9; c += 1) {
		this.grid[r][c] = g[r][c];
	    }
	}
    }

    public Sudoku value() { return this; }

    public boolean isComplete() {
	for (int r = 0; r < 9; r += 1) {
	    for (int c = 0; c < 9; c += 1) {
		if (grid[r][c] == 0) return false;
	    }
	}
	return true;
    }

    private boolean[] candidatesAt(int r, int c) {
	boolean[] used = new boolean[10];
	for (int i = 0; i < 9; i += 1) {
	    if (grid[r][i] != 0) used[grid[r][i]] = true;
	    if (grid[i][c] != 0) used[grid[i][c]] = true;
	}
	int br = (r / 3) * 3, bc = (c / 3) * 3;
	for (int i = 0; i < 3; i += 1) {
	    for (int j = 0; j < 3; j += 1) {
		int v = grid[br + i][bc + j];
		if (v != 0) used[v] = true;
	    }
	}
	boolean[] cands = new boolean[10];
	for (int d = 1; d <= 9; d += 1) cands[d] = !used[d];
	return cands;
    }

    public FnList<FnGtree<Sudoku>> children() {
	// MRV heuristic: among all empty cells, pick the one with
	// the fewest legal candidates. Stop early if a cell has 0
	// or 1 candidate.
	int bestR = -1, bestC = -1;
	int bestCount = 10;
	boolean[] bestCands = null;
	for (int r = 0; r < 9; r += 1) {
	    for (int c = 0; c < 9; c += 1) {
		if (grid[r][c] != 0) continue;
		boolean[] cands = candidatesAt(r, c);
		int cnt = 0;
		for (int d = 1; d <= 9; d += 1) if (cands[d]) cnt += 1;
		if (cnt < bestCount) {
		    bestCount = cnt;
		    bestR = r;
		    bestC = c;
		    bestCands = cands;
		    if (cnt <= 1) break;
		}
	    }
	    if (bestCount <= 1 && bestR >= 0) break;
	}
	FnList<FnGtree<Sudoku>> res = FnListSUtil.nil();
	if (bestR < 0) return res;       // already complete -> leaf
	if (bestCount == 0) return res;  // dead end -> prune
	for (int d = 9; d >= 1; d -= 1) {
	    if (!bestCands[d]) continue;
	    int[][] g2 = new int[9][9];
	    for (int r = 0; r < 9; r += 1) {
		for (int c = 0; c < 9; c += 1) g2[r][c] = grid[r][c];
	    }
	    g2[bestR][bestC] = d;
	    res = FnListSUtil.cons(new Sudoku(g2), res);
	}
	return res.reverse();
    }

    public void show() {
	for (int r = 0; r < 9; r += 1) {
	    if (r % 3 == 0 && r > 0) System.out.println("------+-------+------");
	    for (int c = 0; c < 9; c += 1) {
		if (c % 3 == 0 && c > 0) System.out.print("| ");
		System.out.print(grid[r][c] == 0 ? ". " : (grid[r][c] + " "));
	    }
	    System.out.println();
	}
    }
}

public class Assign11_01 {

    public LnStrm<Sudoku> Soduku_dfs_solve(Sudoku puzzle) {
	LnStrm<Sudoku> all = FnGtreeSUtil.DFirstEnumerate(puzzle);
	return LnStrmSUtil.filter0(all, (Sudoku s) -> s.isComplete());
    }

    public LnStrm<Sudoku> Soduku_bfs_solve(Sudoku puzzle) {
	LnStrm<Sudoku> all = FnGtreeSUtil.BFirstEnumerate(puzzle);
	return LnStrmSUtil.filter0(all, (Sudoku s) -> s.isComplete());
    }
//
    private static int[][] parsePuzzle(String[] rows) {
	int[][] g = new int[9][9];
	for (int r = 0; r < 9; r += 1) {
	    String row = rows[r];
	    for (int c = 0; c < 9; c += 1) {
		char ch = row.charAt(c);
		g[r][c] = (ch >= '1' && ch <= '9') ? (ch - '0') : 0;
	    }
	}
	return g;
    }

    private static void runDFS(Assign11_01 solver, String name, Sudoku puzzle) {
	System.out.println("=== DFS: " + name + " ===");
	System.out.println("Puzzle:");
	puzzle.show();
	long t0 = System.currentTimeMillis();
	LnStrm<Sudoku> sols = solver.Soduku_dfs_solve(puzzle);
	LnStcn<Sudoku> first = sols.eval0();
	long t1 = System.currentTimeMillis();
	if (first.consq()) {
	    System.out.println("First DFS solution (in " + (t1 - t0) + " ms):");
	    first.hd().show();
	} else {
	    System.out.println("No DFS solution found (in " + (t1 - t0) + " ms).");
	}
    }
    private static void runBFS(Assign11_01 solver, String name, Sudoku puzzle) {
	System.out.println("=== BFS: " + name + " ===");
	long t0 = System.currentTimeMillis();
	LnStrm<Sudoku> sols = solver.Soduku_bfs_solve(puzzle);
	LnStcn<Sudoku> first = sols.eval0();
	long t1 = System.currentTimeMillis();
	if (first.consq()) {
	    System.out.println("First BFS solution (in " + (t1 - t0) + " ms):");
	    first.hd().show();
	} else {
	    System.out.println("No BFS solution found (in " + (t1 - t0) + " ms).");
	}
    }
//
    public static void main (String[] args) {
	Assign11_01 solver = new Assign11_01();

	// An "easy" puzzle.
	String[] easy = {
	    "53..7....",
	    "6..195...",
	    ".98....6.",
	    "8...6...3",
	    "4..8.3..1",
	    "7...2...6",
	    ".6....28.",
	    "...419..5",
	    "....8..79"
	};
	Sudoku easyPuzzle = new Sudoku(parsePuzzle(easy));
	runDFS(solver, "easy", easyPuzzle);
	runBFS(solver, "easy", easyPuzzle);

	// A "hard" puzzle (Arto Inkala's near-worst-case hard puzzle).
	String[] hard = {
	    "8........",
	    "..36.....",
	    ".7..9.2..",
	    ".5...7...",
	    "....457..",
	    "...1...3.",
	    "..1....68",
	    "..85...1.",
	    ".9....4.."
	};
	Sudoku hardPuzzle = new Sudoku(parsePuzzle(hard));
	runDFS(solver, "hard", hardPuzzle);
	runBFS(solver, "hard", hardPuzzle);
    }
//
}
