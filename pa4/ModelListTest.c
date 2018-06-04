#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <signal.h>
#include <setjmp.h>

#include "List.h"

#define FIRST_TEST Empty_length
#define MAXSCORE 20

static uint8_t testsPassed;
static volatile sig_atomic_t testStatus;
static uint8_t disable_exit_handler;
jmp_buf test_crash;

enum Test_e {
  Empty_length = 0,
  Append_length,
  Prepend_length,
  InsertAfter_length,
  InsertBefore_length,
  DeleteFront_length,
  DeleteBack_length,
  Delete_length,

  EmptyList_index,
  MoveFront_index,
  MoveBack_index,
  MoveNext_index,
  MovePrev_index,
  Append_index,
  Prepend_index,
  InsertAfter_index,
  InsertBefore_index,
  DeleteFront_index,
  DeleteBack_index,
  Delete_index,

  Append_equals,
  Prepend_equals,
  InsertAfter_equals,
  InsertBefore_equals,
  DeleteFront_equals,
  DeleteBack_equals,
  Delete_equals,

  Empty_clear,
  NonEmpty_clear,

  Set_get,
  Set_front,
  NonEmpty_front,
  Set_back,
  NonEmpty_back,

  Empty_copyList,
  NonEmpty_copyList,

  NUM_TESTS,
};

char *testName(int test) {
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

  if (test == Empty_copyList) return "Empty_copyList";
  if (test == NonEmpty_copyList) return "NonEmpty_copyList";

  return "";
}

uint8_t runTest(List *pA, List *pB, int test) {
  List A = *pA;
  List B = *pB;
  switch(test) {
    case Empty_length:
      {
        if (length(A) != 0) return 1;
        return 0;
      }
    case Append_length:
      {
        append(A, 1);
        append(A, 2);
        append(A, 3);
        append(A, 5);
        if (length(A) != 4) return 1;
        return 0;
      }
    case Prepend_length:
      {
        prepend(A, 6);
        prepend(A, 4);
        prepend(A, 2);
        prepend(A, 1);
        if (length(A) != 4) return 1;
        return 0;
      }
    case InsertAfter_length:
      {
        append(A, 1);
        append(A, 2);
        append(A, 3);
        append(A, 5);
        moveFront(A);
        insertAfter(A, 12);
        if (length(A) != 5) return 1;
        return 0;
      }
    case InsertBefore_length:
      {
        prepend(A, 76);
        prepend(A, 4);
        prepend(A, 3);
        prepend(A, 1);
        moveFront(A);
        insertBefore(A, 115);
        if (length(A) != 5) return 1;
        return 0;
      }
    case DeleteFront_length:
      {
        prepend(A, 76);
        prepend(A, 4);
        deleteFront(A);
        prepend(A, 3);
        prepend(A, 1);
        moveFront(A);
        insertBefore(A, 115);
        deleteFront(A);
        if (length(A) != 3) return 1;
        return 0;
      }
    case DeleteBack_length:
      {
        append(A, 1);
        deleteBack(A);
        append(A, 2);
        append(A, 3);
        append(A, 5);
        moveFront(A);
        insertAfter(A, 12);
        deleteBack(A);
        if (length(A) != 3) return 1;
        return 0;
      }
    case Delete_length:
      {
        append(A, 1);
        append(A, 2);
        moveFront(A);
        delete(A);
        append(A, 3);
        append(A, 5);
        moveFront(A);
        insertAfter(A, 12);
        delete(A);
        if (length(A) != 3) return 1;
        return 0;
      }
    case EmptyList_index:
      {
        if (index(A) != -1) return 1;
        return 0;
      }
    case MoveFront_index:
      {
        append(A, 1);
        append(A, 5);
        append(A, 16);
        append(A, 176);
        append(A, 3214);
        moveFront(A);
        if (index(A) != 0) return 1;
        return 0;
      }
    case MoveBack_index:
      {
        append(A, 1);
        append(A, 5);
        append(A, 16);
        append(A, 176);
        append(A, 3214);
        moveBack(A);
        if (index(A) != 4) return 1;
        return 0;
      }
    case MoveNext_index:
      {
        append(A, 1);
        append(A, 5);
        append(A, 16);
        append(A, 176);
        append(A, 3214);
        moveFront(A);
        moveNext(A);
        moveNext(A);
        if (index(A) != 2) return 1;
        moveNext(A);
        moveNext(A);
        moveNext(A);
        if (index(A) != -1) return 2;
        return 0;
      }
    case MovePrev_index:
      {
        append(A, 1);
        append(A, 5);
        append(A, 3214);
        moveBack(A);
        movePrev(A);
        if (index(A) != 1) return 1;
        movePrev(A);
        movePrev(A);
        if (index(A) != -1) return 2;
        return 0;
      }
    case Append_index:
      {
        append(A, 1);
        append(A, 5);
        append(A, 7);
        moveBack(A);
        append(A, 45);
        append(A, 51);
        append(A, 3214);
        if (index(A) != 2) return 1;
        moveBack(A);
        movePrev(A);
        movePrev(A);
        if (index(A) != 3) return 2;
        moveFront(A);
        movePrev(A);
        if (index(A) != -1) return 3;
        return 0;
      }
    case Prepend_index:
      {
        prepend(A, 1);
        prepend(A, 5);
        prepend(A, 7);
        moveFront(A);
        prepend(A, 45);
        prepend(A, 51);
        prepend(A, 3214);
        prepend(A, 314);
        prepend(A, 324);
        if (index(A) != 5) return 1;
        moveBack(A);
        movePrev(A);
        prepend(A, 234);
        movePrev(A);
        if (index(A) != 6) return 2;
        moveFront(A);
        movePrev(A);
        if (index(A) != -1) return 3;
        return 0;
      }
    case InsertAfter_index:
      {
        append(A, 5);
        append(A, 6);
        append(A, 4);
        append(A, 33);
        append(A, 2);
        append(A, 1);
        moveBack(A);
        insertAfter(A, 75);
        moveNext(A);
        if (index(A) != 6) return 1;
        insertAfter(A, 345);
        moveBack(A);
        if (index(A) != 7) return 2;
        return 0;
      }
    case InsertBefore_index:
      {
        prepend(A, 34);
        prepend(A, 4);
        prepend(A, 354);
        prepend(A, 3674);
        moveBack(A);
        insertBefore(A, 435);
        if (index(A) != 4) return 1;
        prepend(A, 324);
        prepend(A, 33464);
        prepend(A, 3498);
        moveFront(A);
        insertBefore(A, 67);
        if (index(A) != 1) return 2;
        return 0;
      }
    case DeleteFront_index:
      {
        prepend(A, 5);
        prepend(A, 65);
        prepend(A, 43);
        prepend(A, 2);
        prepend(A, 8);
        prepend(A, 1);
        moveFront(A);
        deleteFront(A);
        if (index(A) != -1) return 1;
        moveBack(A);
        deleteFront(A);
        if (index(A) != 3) return 2;
        return 0;
      }
    case DeleteBack_index:
      {
        prepend(A, 5);
        prepend(A, 65);
        prepend(A, 43);
        prepend(A, 2);
        prepend(A, 8);
        prepend(A, 1);
        moveBack(A);
        deleteBack(A);
        if (index(A) != -1) return 1;
        moveFront(A);
        deleteBack(A);
        moveNext(A);
        if (index(A) != 1) return 2;
        return 0;
      }
    case Delete_index:
      {
        prepend(A, 5);
        prepend(A, 65);
        prepend(A, 43);
        moveBack(A);
        delete(A);
        if (index(A) != -1) return 1;
        prepend(A, 2);
        prepend(A, 8);
        prepend(A, 1);
        moveBack(A);
        if (index(A) != 4) return 2;
        delete(A);
        moveBack(A);
        if (index(A) != 3) return 3;
        moveFront(A);
        delete(A);
        moveFront(A);
        if (index(A) != 0) return 4;
        delete(A);
        if (index(A) != -1) return 5;
        return 0;
      }
    case Append_equals:
      {
        append(A, 1);
        append(B, 1);
        append(A, 2);
        if (equals(A, B)) return 1;
        append(B, 2);
        if (!equals(A, B)) return 2;
        return 0;
      }
    case Prepend_equals:
      {
        prepend(A, 1);
        prepend(B, 1);
        prepend(A, 2);
        if (equals(A, B)) return 1;
        prepend(B, 2);
        if (!equals(A, B)) return 2;
        return 0;
      }
    case InsertAfter_equals:
      {
        append(A, 1);
        append(B, 1);
        append(A, 2);
        moveFront(B);
        insertAfter(B, 2);
        if (!equals(A, B)) return 1;
        append(B, 3);
        moveBack(A);
        insertAfter(A, 3);
        if (!equals(A, B)) return 2;
        return 0;
      }
    case InsertBefore_equals:
      {
        prepend(A, 1);
        prepend(B, 1);
        prepend(A, 2);
        moveFront(B);
        insertBefore(B, 2);
        if (!equals(A, B)) return 1;
        prepend(B, 3);
        moveFront(A);
        insertBefore(A, 3);
        if (!equals(A, B)) return 2;
        return 0;
      }
    case DeleteFront_equals:
      {
        append(A, 1);
        append(B, 1);
        append(A, 2);
        append(B, 2);
        deleteFront(A);
        if (equals(A, B)) return 1;
        deleteFront(B);
        if (!equals(A, B)) return 2;
        prepend(A, 3);
        prepend(B, 3);
        deleteFront(A);
        deleteFront(B);
        if (!equals(A, B)) return 3;
        return 0;
      }
    case DeleteBack_equals:
      {
        prepend(A, 1);
        prepend(B, 1);
        prepend(A, 2);
        prepend(B, 2);
        deleteBack(A);
        if (equals(A, B)) return 1;
        deleteBack(B);
        if (!equals(A, B)) return 2;
        append(A, 3);
        append(B, 3);
        deleteBack(A);
        deleteBack(B);
        if (!equals(A, B)) return 3;
        return 0;
      }
    case Delete_equals:
      {
        prepend(A, 1);
        prepend(B, 1);
        prepend(A, 2);
        prepend(B, 2);
        moveBack(A);
        delete(A);
        if (equals(A, B)) return 1;
        moveBack(B);
        delete(B);
        if (!equals(A, B)) return 2;
        append(A, 3);
        append(B, 3);
        moveBack(A);
        delete(A);
        moveBack(B);
        delete(B);
        if (!equals(A, B)) return 3;
        return 0;
      }
    case Empty_clear:
      {
        clear(A);
        if (index(A) != -1 || length(A) != 0) return 1;
        return 0;
      }
    case NonEmpty_clear:
      {
        append(A, 1);
        prepend(A, 2);
        moveFront(A);
        clear(A);
        if (index(A) != -1 || length(A) != 0) return 1;
        return 0;
      }
    case Set_get:
      {
        append(A, 1);
        prepend(A, 2);
        deleteFront(A);
        moveBack(A);
        if (get(A) != 1) return 1;
        return 0;
      }
    case Set_front:
      {
        append(A, 1);
        prepend(A, 5);
        moveBack(A);
        if (front(A) != 5) return 1;
        return 0;
      }
    case NonEmpty_front:
      {
        prepend(A, 5);
        append(A, 7);
        prepend(A, 2);
        moveFront(A);
        insertBefore(A, 43);
        deleteFront(A);
        delete(A);
        if (front(A) != 5) return 1;
        return 0;
      }
    case Set_back:
      {
        prepend(A, 1);
        append(A, 5);
        moveFront(A);
        if (back(A) != 5) return 1;
        return 0;
      }
    case NonEmpty_back:
      {
        append(A, 5);
        prepend(A, 7);
        append(A, 2);
        moveBack(A);
        insertAfter(A, 43);
        deleteBack(A);
        delete(A);
        if (back(A) != 5) return 1;
        return 0;
      }
    case Empty_copyList:
      {
        List C = copyList(A);
        bool res = !equals(A, C);
        freeList(&C);
        if (res) return 1;
        return 0;
      }
    case NonEmpty_copyList:
      {
        append(A, 2);
        prepend(A, 1);
        moveFront(A);
        List C = copyList(A);
        bool res = (index(A) != 0 || !equals(A, C));
        freeList(&C);
        if (res) return 1;
        return 0;
      }
  }
  return 255;
}

void segfault_handler(int signal) { // everyone knows what this is
  testStatus = 255;
  longjmp(test_crash, 1);
}

void exit_attempt_handler(void) { // only I decide when you are done
  if (disable_exit_handler) return; // allow this to be disabled
  testStatus = 255;
  longjmp(test_crash, 2);
}

void abrupt_termination_handler(int signal) { // program killed externally
  testStatus = 255;
  longjmp(test_crash, 3);
}

int main (int argc, char **argv) {
  if (argc > 2 || (argc == 2 && strcmp(argv[1], "-v") != 0)) {
    printf("Usage: %s [-v]", (argc > 0 ? argv[0] : "./ListTest"));
    exit(1);
  }

  printf("\n"); // more spacing
  if (argc == 2) printf("\n"); // consistency in verbose mode

  testsPassed = 0;
  disable_exit_handler = 0;
  atexit(exit_attempt_handler);
  signal(SIGSEGV, segfault_handler);
  //signal(SIGTERM, abrupt_termination_handler); // dangerous
  //signal(SIGINT, abrupt_termination_handler);
  //signal(SIGFPE, abrupt_termination_handler);
  //signal(SIGABRT, abrupt_termination_handler);
  for (uint8_t i = FIRST_TEST; i < NUM_TESTS; i++) {
    List A = newList();
    List B = newList();
    testStatus = runTest(&A, &B, i);
    freeList(&A);
    freeList(&B);
    uint8_t fail_type = setjmp(test_crash);
    if (argc == 2) { // it's verbose mode
      printf("Test %s: %s", testName(i), testStatus == 0 ? "PASSED" :
          "FAILED");
      if (testStatus == 255) {
        printf(": due to a %s\n", fail_type == 1 ? "segfault" : fail_type == 2 ?
            "program exit" : "program interruption");
        printf("\nWARNING: Program will now stop running tests\n\n");
        break;
      } else if (testStatus != 0) {
        printf(": test %d\n", testStatus);
      } else {
        printf("\n");
      }
    }
    if (testStatus == 0) {
      testsPassed++;
    }
  }

  disable_exit_handler = 1;

  uint8_t totalScore = (MAXSCORE - NUM_TESTS / 2) + testsPassed / 2;

  if (argc == 2 && testStatus != 255) printf("\nYou passed %d out of %d tests\n", testsPassed,
      NUM_TESTS);
  else if (testStatus == 255) {
    totalScore = 0; // charity points
    if (argc == 2) printf("Receiving charity points because your program crashes\n");
  }
  printf("\nYou will receive %d out of %d possible points on the ListTests\n\n",
      totalScore, MAXSCORE);
  exit(0);
  return 0;
}
