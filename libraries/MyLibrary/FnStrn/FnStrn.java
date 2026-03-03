package MyLibrary.FnStrn;

public class FnStrn {

    char root[];

    public FnStrn() {
	root = null;
    }
    public FnStrn(char[] cs) {
	int ln = cs.length;
	root = new char[ln];
	for (int i = 0; i < ln; i += 1) {
	    root[i] = cs[i];
	}
    }
    public FnStrn(String cs) {
	int ln = cs.length();
	root = new char[ln];
	for (int i = 0; i < ln; i += 1) {
	    root[i] = cs.charAt(i);
	}
    }

    public int length() {
	return root.length;
    }

    public char getAt(int index) {
	return root[index];
    }
} // end of [public class FnStrn{...}]
