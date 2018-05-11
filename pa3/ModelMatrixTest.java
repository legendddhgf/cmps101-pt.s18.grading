class ModelMatrixTest {

  static boolean verbose;
  static int testCount;
  static int tests_passed;

  static int Empty_getSize;
  static int NonEmpty_getSize;

  static int Empty_getNNZ;
  static int makeZero_getNNZ;
  static int changeEntry_getNNZ;
  static int copy_getNNZ;
  static int transpose_getNNZ;
  static int add_getNNZ;
  static int sub_getNNZ;
  static int scalarMult_getNNZ;
  static int mult_getNNZ;

  static int Empty_equals;
  static int NonEmpty_equals;

  static int Empty_toString;
  static int NonEmpty_toString;

  static String testName(int test) {

    if (test == Empty_getSize) return "Empty_getSize";
    if (test == NonEmpty_getSize) return "NonEmpty_getSize";

    if (test == Empty_getNNZ) return "Empty_getNNZ";
    if (test == makeZero_getNNZ) return "makeZero_getNNZ";
    if (test == changeEntry_getNNZ) return "changeEntry_getNNZ";
    if (test == copy_getNNZ) return "copy_getNNZ";
    if (test == transpose_getNNZ) return "transpose_getNNZ";
    if (test == add_getNNZ) return "add_getNNZ";
    if (test == sub_getNNZ) return "sub_getNNZ";
    if (test == scalarMult_getNNZ) return "scalarMult_getNNZ";
    if (test == mult_getNNZ) return "mult_getNNZ";

    if (test == Empty_equals) return "Empty_equals";
    if (test == NonEmpty_equals) return "NonEmpty_equals";

    if (test == Empty_toString) return "Empty_toString";
    if (test == NonEmpty_toString) return "NonEmpty_toString";

    return "";
  }

  static int runTest(int test) {

    Matrix A;
    Matrix B;
    Matrix C;
    Matrix D;

    try {
      if (test == Empty_getSize) {
        A = new Matrix(3);
        if (A.getSize() != 3) return 1;
      } else if (test == NonEmpty_getSize) {
        A = new Matrix(3);
        A.changeEntry(1, 1, 4);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 0);
        A.changeEntry(2, 1, 2);
        A.changeEntry(3, 1, 0);
        A.changeEntry(2, 2, 2);
        A.changeEntry(3, 3, 0);
        if (A.getSize() != 3) return 1;
      } else if (test == Empty_getNNZ) {
        A = new Matrix(3);
        if (A.getNNZ() != 0) return 1;
      } else if (test == makeZero_getNNZ) {
        A = new Matrix(3);
        A.changeEntry(1, 1, 4);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 0);
        A.changeEntry(2, 1, 2);
        A.changeEntry(3, 1, 0);
        A.changeEntry(2, 2, 2);
        A.changeEntry(3, 3, 0);
        A.makeZero();
        if (A.getNNZ() != 0) return 1;
      } else if (test == changeEntry_getNNZ) {
        A = new Matrix(10);
        A.changeEntry(2, 1, 2);
        A.changeEntry(3, 1, 5);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 5);
        A.changeEntry(1, 1, 4);
        A.changeEntry(2, 2, 2);
        A.changeEntry(2, 5, 0);
        A.changeEntry(2, 3, 0);
        A.changeEntry(3, 3, 5);
        if (A.getNNZ() != 7) return 1;
        A.changeEntry(1, 3, 0);
        A.changeEntry(3, 1, 0);
        A.changeEntry(3, 3, 0);
        if (A.getNNZ() != 4) return 2;
        A.changeEntry(7, 6, 42);
        A.changeEntry(10, 1, 24);
        if (A.getNNZ() != 6) return 3;
        A.changeEntry(7, 6, 0);
        if (A.getNNZ() != 5) return 4;
        A.makeZero();
        A.changeEntry(5, 5, 5);
        if (A.getNNZ() != 1) return 5;
      } else if (test == copy_getNNZ) {
        A = new Matrix(10);
        B = A.copy();
        if (B.getNNZ() != 0) return 1;
        A.changeEntry(1, 1, 1);
        B = A.copy();
        if (B.getNNZ() != 1) return 2;
      } else if (test == transpose_getNNZ) {
        A = new Matrix(3);
        B = A.transpose();
        if (B.getNNZ() != 0) return 1;
        A.changeEntry(1, 1, 4);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 0);
        A.changeEntry(2, 1, 2);
        A.changeEntry(3, 1, 0);
        A.changeEntry(2, 2, 2);
        A.changeEntry(3, 3, 0);
        B = A.transpose();
        if (B.getNNZ() != 4) return 2;
      } else if (test == add_getNNZ) {
        A = new Matrix(10);
        B = new Matrix(10);
        A.changeEntry(1, 1, 4);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 0);
        A.changeEntry(2, 1, 2);
        A.changeEntry(3, 1, 0);
        A.changeEntry(2, 2, 2);
        A.changeEntry(3, 3, 0);
        C = A.add(A);
        if (C.getNNZ() != 4 || A.getNNZ() != 4) return 1;
        B.changeEntry(1, 1, -4);
        B.changeEntry(1, 2, 0);
        B.changeEntry(2, 1, 0);
        B.changeEntry(2, 2, -2);
        B.changeEntry(2, 4, 2);
        B.changeEntry(3, 1, 0);
        B.changeEntry(3, 2, 2);
        B.changeEntry(7, 8, 5);
        C = A.add(B);
        if (C.getNNZ() != 5) return 2;
      } else if (test == sub_getNNZ) {
        A = new Matrix(10);
        B = new Matrix(10);
        A.changeEntry(1, 1, -4);
        A.changeEntry(1, 2, -2);
        A.changeEntry(1, 3, 0);
        A.changeEntry(2, 5, 4);
        A.changeEntry(2, 1, -2);
        A.changeEntry(3, 1, 2);
        A.changeEntry(2, 2, -2);
        A.changeEntry(3, 3, 0);
        C = A.sub(A);
        if (C.getNNZ() != 0 || A.getNNZ() != 6) return 1;
        B.changeEntry(1, 1, -4);
        B.changeEntry(1, 2, 0);
        B.changeEntry(2, 1, 0);
        B.changeEntry(2, 2, -2);
        B.changeEntry(2, 4, 2);
        B.changeEntry(3, 1, 2);
        B.changeEntry(3, 2, 2);
        B.changeEntry(7, 8, 5);
        C = A.sub(B);
        if (C.getNNZ() != 6) return 2;
      } else if (test == scalarMult_getNNZ) {
        A = new Matrix(10);
        A.changeEntry(1, 1, 4);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 0);
        A.changeEntry(2, 1, 2);
        A.changeEntry(3, 1, 0);
        A.changeEntry(2, 2, 2);
        A.changeEntry(3, 3, 0);
        B = A.scalarMult(-20);
        if (B.getNNZ() != 4) return 1;
      } else if (test == mult_getNNZ) {
        A = new Matrix(10);
        B = new Matrix(10);
        A.changeEntry(1, 1, 1);
        A.changeEntry(2, 2, 1);
        A.changeEntry(3, 3, 1);
        C = A.mult(A);
        if (C.getNNZ() != 3) return 1;
        A.changeEntry(1, 1, 1);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 3);
        A.changeEntry(2, 1, 4);
        A.changeEntry(2, 2, 5);
        A.changeEntry(2, 3, 6);
        A.changeEntry(3, 1, 7);
        A.changeEntry(3, 2, 8);
        A.changeEntry(3, 3, 9);
        B.changeEntry(1, 1, 1);
        B.changeEntry(2, 2, 1);
        C = A.mult(B);
        if (C.getNNZ() != 6) return 2;
      } else if (test == Empty_equals) {
        A = new Matrix(10);
        B = new Matrix(15);
        if (A.equals(B)) return 1;
        B = new Matrix(10);
        if (!A.equals(B)) return 2;
        A.changeEntry(5, 5, 5);
        B.changeEntry(5, 5, 5);
        A.makeZero();
        B.makeZero();
        if (!A.equals(B)) return 3;
      } else if (test == NonEmpty_equals) {
        A = new Matrix(10);
        B = new Matrix(15);
        A.changeEntry(1, 1, 1);
        B.changeEntry(1, 1, 1);
        if (A.equals(B)) return 1;
        B = new Matrix(10);
        A.changeEntry(1, 1, 1);
        A.changeEntry(1, 3, 1);
        B.changeEntry(1, 1, 1);
        B.changeEntry(1, 3, 1);
        if (!A.equals(B)) return 2;
        A.changeEntry(1, 3, 0);
        if (A.equals(B)) return 3;
        A.changeEntry(1, 1, 0);
        B.makeZero();
        A.changeEntry(10, 10, 10);
        B.changeEntry(10, 10, 10);
        if (!A.equals(B)) return 4;
        A = new Matrix(100);
        B = new Matrix(100);
        int valcount = 1;
        for (int j = 1; j <= 100; j++) {
          for (int k = 1; k <= 100; k++) {
            // hint: this is 1-10000 left-to-right row-by-row
            A.changeEntry(j, k, valcount++);
          }
          B.changeEntry(j, j, 1); // hint: this is the identity matrix
        }
        C = A.scalarMult(2);
        if (!C.equals(A.add(A))) return 5;
        C = A.scalarMult(-2);
        if (!C.equals(A.sub(A).sub(A).sub(A))) return 6;
        C = A.mult(B);
        if (!C.equals(A)) return 7;
      } else if (test == Empty_toString) {
        A = new Matrix(100);
        if (!A.toString().equals("")) return 1;
        A.changeEntry(4, 2, 42);
        A.makeZero();
        if (!A.toString().equals("")) return 2;
      } else if (test == NonEmpty_toString) {
        A = new Matrix(10);
        A.changeEntry(1, 1, 1);
        A.changeEntry(1, 2, 0);
        A.changeEntry(1, 3, 3.5);
        A.changeEntry(5, 7, 2.5);
        A.changeEntry(3, 4, 2);
        A.changeEntry(1, 1, 2);
        if (!A.toString().replaceAll("\\s+","").equals(
              "1: (1, 2.0) (3, 3.5)\n3: (4, 2.0)\n5: (7, 2.5)\n".replaceAll(
                "\\s+","")
              )) return 1;
      }
    } catch (Exception e) {
      if (verbose) {
        System.out.println("\nUnfortunately your program crashed on test " + testName(test) + " With an exception of:\n");
        e.printStackTrace();
        System.out.println();
      }
      return 255;
    }
    return 0;
  }

  public static void main(String args[]) {

    if (args.length > 1 || (args.length == 1 && !args[0].equals("-v"))) {
      System.err.println("Usage: ./MatrixTest [-v]");
      System.exit(1);
    }
    verbose = false;
    if (args.length == 1) verbose = true;

    testCount = 0;
    tests_passed = 0;
    // form is TESTCASE_FUNCTION
    Empty_getSize = testCount++;
    NonEmpty_getSize = testCount++;

    Empty_getNNZ = testCount++;
    makeZero_getNNZ = testCount++;
    changeEntry_getNNZ = testCount++;
    copy_getNNZ = testCount++;
    transpose_getNNZ = testCount++;
    add_getNNZ = testCount++;
    sub_getNNZ = testCount++;
    scalarMult_getNNZ = testCount++;
    mult_getNNZ = testCount++;

    Empty_equals = testCount++;
    NonEmpty_equals = testCount++;

    Empty_toString = testCount++;
    NonEmpty_toString = testCount++;

    int test_status = 255;
    if (verbose) {
      System.out.println("\nList of tests passed/failed:\n");
    }
    for (int i = 0; i < testCount; i++) {
      test_status = runTest(i);
      if (verbose)
        System.out.printf("%s %s", testName(i),
            test_status == 0 ? "PASSED" : "FAILED");
      if (test_status == 0) {
        if (verbose) System.out.printf("\n");
        tests_passed++;
      } else if (test_status == 255) {
        if (verbose) System.out.printf(": due to exception\n");
        break;
      } else {
        if (verbose) System.out.printf(": in test %d\n", test_status);
      }
    }

    final int maxScore = 60;

    int totalPoints = (maxScore - testCount * 4) + tests_passed * 4; // four points per test

    if (verbose && test_status != 255) {
      System.out.printf("\nPassed %d tests out of %d possible\n", tests_passed, testCount);
    } else if (test_status == 255) {
      if (verbose) {
        System.out.printf("\nCharity points because you had an exception\n", tests_passed, testCount);
      }
      totalPoints = 0;
    }
    System.out.printf("\nThis gives you a score of %d out of %d for MatrixTest\n\n", totalPoints, maxScore);
  }
}

