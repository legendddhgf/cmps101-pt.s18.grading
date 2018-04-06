import java.util.BitSet;

class ModelListTest {

  static int test_count;

  static boolean verbose;

  static int Empty_length;
  static int Append_length;
  static int Prepend_length;
  static int InsertAfter_length;
  static int InsertBefore_length;
  static int DeleteFront_length;
  static int DeleteBack_length;
  static int Delete_length;

  static int EmptyList_index;
  static int MoveFront_index;
  static int MoveBack_index;
  static int MoveNext_index;
  static int MovePrev_index;
  static int Append_index;
  static int Prepend_index;
  static int InsertAfter_index;
  static int InsertBefore_index;
  static int DeleteFront_index;
  static int DeleteBack_index;
  static int Delete_index;

  static int Append_equals;
  static int Prepend_equals;
  static int InsertAfter_equals;
  static int InsertBefore_equals;
  static int DeleteFront_equals;
  static int DeleteBack_equals;
  static int Delete_equals;

  static int Empty_clear;
  static int NonEmpty_clear;

  static int Set_get;
  static int Set_front;
  static int NonEmpty_front;
  static int Set_back;
  static int NonEmpty_back;

  static int Empty_copy;
  static int NonEmpty_copy;

  static int Empty_toString;
  static int NonEmpty_toString;


  static String testName(int test) {
    if (test == Empty_length) return "Empty_length";
    if (test == Append_length) return "Append_length";
    if (test == Prepend_length) return "Prepend_length";
    if (test == InsertAfter_length) return "InsertAfter_length";
    if (test == InsertBefore_length) return "InsertBefore_length";
    if (test == DeleteFront_length) return "DeleteFront_length";
    if (test == DeleteBack_length) return "DeleteBack_length";
    if (test == Delete_length) return "Delete_length";

    if (test == EmptyList_index) return "EmptyList_index";
    if (test == MoveFront_index) return "MoveFront_index";
    if (test == MoveBack_index) return "MoveBack_index";
    if (test == MoveNext_index) return "MoveNext_index";
    if (test == MovePrev_index) return "MovePrev_index";
    if (test == Append_index) return "Append_index";
    if (test == Prepend_index) return "Prepend_index";
    if (test == InsertAfter_index) return "InsertAfter_index";
    if (test == InsertBefore_index) return "InsertBefore_index";
    if (test == DeleteFront_index) return "DeleteFront_index";
    if (test == DeleteBack_index) return "DeleteBack_index";
    if (test == Delete_index) return "Delete_index";

    if (test == Append_equals) return "Append_equals";
    if (test == Prepend_equals) return "Prepend_equals";
    if (test == InsertAfter_equals) return "InsertAfter_equals";
    if (test == InsertBefore_equals) return "InsertBefore_equals";
    if (test == DeleteFront_equals) return "DeleteFront_equals";
    if (test == DeleteBack_equals) return "DeleteBack_equals";
    if (test == Delete_equals) return "Delete_equals";

    if (test == Empty_clear) return "Empty_clear";
    if (test == NonEmpty_clear) return "NonEmpty_clear";

    if (test == Set_get) return "Set_get";
    if (test == Set_front) return "Set_front";
    if (test == NonEmpty_front) return "NonEmpty_front";
    if (test == Set_back) return "Set_back";
    if (test == NonEmpty_back) return "NonEmpty_back";

    if (test == Empty_copy) return "Empty_copy";
    if (test == NonEmpty_copy) return "NonEmpty_copy";

    if (test == Empty_toString) return "Empty_toString";
    if (test == NonEmpty_toString) return "NonEmpty_toString";

    return "";
  }

  public static int runTest(int test) {

    List A;
    List B;

    try {
      if (test == Empty_length) {
        A = new List();
        if (A.length() != 0) return 1;
      } else if (test == Append_length) {
        A = new List();
        A.append(1);
        A.append(2);
        A.append(3);
        A.append(5);
        if (A.length() != 4) return 1;
      } else if (test == Prepend_length) {
        A = new List();
        A.prepend(6);
        A.prepend(4);
        A.prepend(2);
        A.prepend(1);
        if (A.length() != 4) return 1;
      } else if (test == InsertAfter_length) {
        A = new List();
        A.append(1);
        A.append(2);
        A.append(3);
        A.append(5);
        A.moveFront();
        A.insertAfter(12);
        if (A.length() != 5) return 1;
      } else if (test == InsertBefore_length) {
        A = new List();
        A.prepend(76);
        A.prepend(4);
        A.prepend(3);
        A.prepend(1);
        A.moveFront();
        A.insertBefore(115);
        if (A.length() != 5) return 1;
      } else if (test == DeleteFront_length) {
        A = new List();
        A.prepend(76);
        A.prepend(4);
        A.deleteFront();
        A.prepend(3);
        A.prepend(1);
        A.moveFront();
        A.insertBefore(115);
        A.deleteFront();
        if (A.length() != 3) return 1;
      } else if (test == DeleteBack_length) {
        A = new List();
        A.append(1);
        A.deleteBack();
        A.append(2);
        A.append(3);
        A.append(5);
        A.moveFront();
        A.insertAfter(12);
        A.deleteBack();
        if (A.length() != 3) return 1;
      } else if (test == Delete_length) {
        A = new List();
        A.append(1);
        A.append(2);
        A.moveFront();
        A.delete();
        A.append(3);
        A.append(5);
        A.moveFront();
        A.insertAfter(12);
        A.delete();
        if (A.length() != 3) return 1;
      } else if (test == EmptyList_index) {
        A = new List();
        if (A.index() != -1) return 1;
      } else if (test == MoveFront_index) {
        A = new List();
        A.append(1);
        A.append(5);
        A.append(16);
        A.append(176);
        A.append(3214);
        A.moveFront();
        if (A.index() != 0) return 1;
      } else if (test == MoveBack_index) {
        A = new List();
        A.append(1);
        A.append(5);
        A.append(16);
        A.append(176);
        A.append(3214);
        A.moveBack();
        if (A.index() != 4) return 1;
      } else if (test == MoveNext_index) {
        A = new List();
        A.append(1);
        A.append(5);
        A.append(16);
        A.append(176);
        A.append(3214);
        A.moveFront();
        A.moveNext();
        A.moveNext();
        if (A.index() != 2) return 1;
        A.moveNext();
        A.moveNext();
        A.moveNext();
        if (A.index() != -1) return 2;
      } else if (test == MovePrev_index) {
        A = new List();
        A.append(1);
        A.append(5);
        A.append(3214);
        A.moveBack();
        A.movePrev();
        if (A.index() != 1) return 1;
        A.movePrev();
        A.movePrev();
        if (A.index() != -1) return 2;
      } else if (test == Append_index) {
        A = new List();
        A.append(1);
        A.append(5);
        A.append(7);
        A.moveBack();
        A.append(45);
        A.append(51);
        A.append(3214);
        if (A.index() != 2) return 1;
        A.moveBack();
        A.movePrev();
        A.movePrev();
        if (A.index() != 3) return 2;
        A.moveFront();
        A.movePrev();
        if (A.index() != -1) return 3;
      } else if (test == Prepend_index) {
        A = new List();
        A.prepend(1);
        A.prepend(5);
        A.prepend(7);
        A.moveFront();
        A.prepend(45);
        A.prepend(51);
        A.prepend(3214);
        A.prepend(314);
        A.prepend(324);
        if (A.index() != 5) return 1;
        A.moveBack();
        A.movePrev();
        A.prepend(234);
        A.movePrev();
        if (A.index() != 6) return 2;
        A.moveFront();
        A.movePrev();
        if (A.index() != -1) return 3;
      } else if (test == InsertAfter_index) {
        A = new List();
        A.append(5);
        A.append(6);
        A.append(4);
        A.append(33);
        A.append(2);
        A.append(1);
        A.moveBack();
        A.insertAfter(75);
        A.moveNext();
        if (A.index() != 6) return 1;
        A.insertAfter(345);
        A.moveBack();
        if (A.index() != 7) return 2;
      } else if (test == InsertBefore_index) {
        A = new List();
        A.prepend(34);
        A.prepend(4);
        A.prepend(354);
        A.prepend(3674);
        A.moveBack();
        A.insertBefore(435);
        if (A.index() != 4) return 1;
        A.prepend(324);
        A.prepend(33464);
        A.prepend(3498);
        A.moveFront();
        A.insertBefore(67);
        if (A.index() != 1) return 2;
      } else if (test == DeleteFront_index) {
        A = new List();
        A.prepend(5);
        A.prepend(65);
        A.prepend(43);
        A.prepend(2);
        A.prepend(8);
        A.prepend(1);
        A.moveFront();
        A.deleteFront();
        if (A.index() != -1) return 1;
        A.moveBack();
        A.deleteFront();
        if (A.index() != 3) return 2;
      } else if (test == DeleteBack_index) {
        A = new List();
        A.prepend(5);
        A.prepend(65);
        A.prepend(43);
        A.prepend(2);
        A.prepend(8);
        A.prepend(1);
        A.moveBack();
        A.deleteBack();
        if (A.index() != -1) return 1;
        A.moveFront();
        A.deleteBack();
        A.moveNext();
        if (A.index() != 1) return 2;
      } else if (test == Delete_index) {
        A = new List();
        A.prepend(5);
        A.prepend(65);
        A.prepend(43);
        A.moveBack();
        A.delete();
        if (A.index() != -1) return 1;
        A.prepend(2);
        A.prepend(8);
        A.prepend(1);
        A.moveBack();
        if (A.index() != 4) return 2;
        A.delete();
        A.moveBack();
        if (A.index() != 3) return 3;
        A.moveFront();
        A.delete();
        A.moveFront();
        if (A.index() != 0) return 4;
        A.delete();
        if (A.index() != -1) return 5;
      } else if (test == Append_equals) {
        A = new List();
        B = new List();
        A.append(1);
        B.append(1);
        A.append(2);
        if (A.equals(B)) return 1;
        B.append(2);
        if (!A.equals(B)) return 2;
      } else if (test == Prepend_equals) {
        A = new List();
        B = new List();
        A.prepend(1);
        B.prepend(1);
        A.prepend(2);
        if (A.equals(B)) return 1;
        B.prepend(2);
        if (!A.equals(B)) return 2;
      } else if (test == InsertAfter_equals) {
        A = new List();
        B = new List();
        A.append(1);
        B.append(1);
        A.append(2);
        B.moveFront();
        B.insertAfter(2);
        if (!A.equals(B)) return 1;
        B.append(3);
        A.moveBack();
        A.insertAfter(3);
        if (!A.equals(B)) return 2;
      } else if (test == InsertBefore_equals) {
        A = new List();
        B = new List();
        A.prepend(1);
        B.prepend(1);
        A.prepend(2);
        B.moveFront();
        B.insertBefore(2);
        if (!A.equals(B)) return 1;
        B.prepend(3);
        A.moveFront();
        A.insertBefore(3);
        if (!A.equals(B)) return 2;
      } else if (test == DeleteFront_equals) {
        A = new List();
        B = new List();
        A.append(1);
        B.append(1);
        A.append(2);
        B.append(2);
        A.deleteFront();
        if (A.equals(B)) return 1;
        B.deleteFront();
        if (!A.equals(B)) return 2;
        A.prepend(3);
        B.prepend(3);
        A.deleteFront();
        B.deleteFront();
        if (!A.equals(B)) return 3;
      } else if (test == DeleteBack_equals) {
        A = new List();
        B = new List();
        A.prepend(1);
        B.prepend(1);
        A.prepend(2);
        B.prepend(2);
        A.deleteBack();
        if (A.equals(B)) return 1;
        B.deleteBack();
        if (!A.equals(B)) return 2;
        A.append(3);
        B.append(3);
        A.deleteBack();
        B.deleteBack();
        if (!A.equals(B)) return 3;
      } else if (test == Delete_equals) {
        A = new List();
        B = new List();
        A.prepend(1);
        B.prepend(1);
        A.prepend(2);
        B.prepend(2);
        A.moveBack();
        A.delete();
        if (A.equals(B)) return 1;
        B.moveBack();
        B.delete();
        if (!A.equals(B)) return 2;
        A.append(3);
        B.append(3);
        A.moveBack();
        A.delete();
        B.moveBack();
        B.delete();
        if (!A.equals(B)) return 3;
      } else if (test == Empty_clear) {
        A = new List();
        A.clear();
        if (A.index() != -1 || A.length() != 0) return 1;
      } else if (test == NonEmpty_clear) {
        A = new List();
        A.append(1);
        A.prepend(2);
        A.moveFront();
        A.clear();
        if (A.index() != -1 || A.length() != 0) return 1;
      } else if (test == Set_get) {
        A = new List();
        A.append(1);
        A.prepend(2);
        A.deleteFront();
        A.moveBack();
        if (A.get() != 1) return 1;
      } else if (test == Set_front) {
        A = new List();
        A.append(1);
        A.prepend(5);
        A.moveBack();
        if (A.front() != 5) return 1;
      } else if (test == NonEmpty_front) {
        A = new List();
        A.prepend(5);
        A.append(7);
        A.prepend(2);
        A.moveFront();
        A.insertBefore(43);
        A.deleteFront();
        A.delete();
        if (A.front() != 5) return 1;
      } else if (test == Set_back) {
        A = new List();
        A.prepend(1);
        A.append(5);
        A.moveFront();
        if (A.back() != 5) return 1;
      } else if (test == NonEmpty_back) {
        A = new List();
        A.append(5);
        A.prepend(7);
        A.append(2);
        A.moveBack();
        A.insertAfter(43);
        A.deleteBack();
        A.delete();
        if (A.back() != 5) return 1;
      } else if (test == Empty_copy) {
        A = new List();
        B = A.copy();
        if (!A.equals(B)) return 1;
      } else if (test == NonEmpty_copy) {
        A = new List();
        A.append(2);
        A.prepend(1);
        A.moveFront();
        B = A.copy();
        if (A.index() != 0 || !A.equals(B)) return 1;
      } else if (test == Empty_toString) {
        A = new List();
        if (!A.toString().trim().equals("")) return 1;
      } else if (test == NonEmpty_toString) {
        A = new List();
        A.append(1);
        A.prepend(5);
        A.deleteBack();
        A.append(7);
        A.append(1);
        if (!A.toString().trim().equals("5 7 1")) return 1;
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
      System.err.println("Usage: ./ListTest [-v]");
      System.exit(1);
    }
    verbose = false;
    if (args.length == 1) verbose = true;

    test_count = 0;
    // form is TESTCASE_FUNCTION
    Empty_length = test_count++;
    Append_length = test_count++;
    Prepend_length = test_count++;
    InsertAfter_length = test_count++;
    InsertBefore_length = test_count++;
    DeleteFront_length = test_count++;
    DeleteBack_length = test_count++;
    Delete_length = test_count++;

    EmptyList_index = test_count++;
    MoveFront_index = test_count++;
    MoveBack_index = test_count++;
    MoveNext_index = test_count++;
    MovePrev_index = test_count++;
    Append_index = test_count++;
    Prepend_index = test_count++;
    InsertAfter_index = test_count++;
    InsertBefore_index = test_count++;
    DeleteFront_index = test_count++;
    DeleteBack_index = test_count++;
    Delete_index = test_count++;

    Append_equals = test_count++;
    Prepend_equals = test_count++;
    InsertAfter_equals = test_count++;
    InsertBefore_equals = test_count++;
    DeleteFront_equals = test_count++;
    DeleteBack_equals = test_count++;
    Delete_equals = test_count++;

    Empty_clear = test_count++;
    NonEmpty_clear = test_count++;

    Set_get = test_count++;
    Set_front = test_count++;
    NonEmpty_front = test_count++;
    Set_back = test_count++;
    NonEmpty_back = test_count++;

    Empty_copy = test_count++;
    NonEmpty_copy = test_count++;

    Empty_toString = test_count++;
    NonEmpty_toString = test_count++;

    int tests_passed = 0;
    int test_status = 0;
    if (verbose)
      System.out.println("\nList of tests passed/failed:\n");
    for (int i = 0; i < test_count; i++) {
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
    System.out.println();

    final int maxScore = 55;

    int totalPoints = (maxScore - test_count) + tests_passed;

    if (test_status == 255) {
      System.out.printf("\nReceiving charity points because of an Exception\n");
      totalPoints = 5;
    } else {
      System.out.printf("\nPassed %d tests out of %d possible\n", tests_passed, test_count);
    }

    System.out.printf("\nThis gives you a score of %d out of %d for this component of the assignment\n\n", totalPoints, maxScore);
  }
}

