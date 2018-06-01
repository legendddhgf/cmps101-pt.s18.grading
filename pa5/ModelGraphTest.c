#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <signal.h>
#include <setjmp.h>

#include "Graph.h"
#include "List.h"

#define FIRST_TEST DG_getSize
#define MAXSCORE 40

static uint8_t testsPassed;
static volatile sig_atomic_t testStatus;
static uint8_t disable_exit_handler;
jmp_buf test_crash;

enum Test_e {
  DG_getSize = 0, // directed graph tests
  DG_getParent,
  DG_getDiscover,
  DG_getFinish,

  UG_getSize, // undirected graph tests
  UG_getParent,
  UG_getDiscover,
  UG_getFinish,

  NUM_TESTS,
};


char *testName(int test) {

  if (test == DG_getSize) return "DG_getSize";
  if (test == DG_getParent) return "DG_getParent";
  if (test == DG_getDiscover) return "DG_getDiscover";
  if (test == DG_getFinish) return "DG_getFinish";

  if (test == UG_getSize) return "UG_getSize";
  if (test == UG_getParent) return "UG_getParent";
  if (test == UG_getDiscover) return "UG_getDiscover";
  if (test == UG_getFinish) return "UG_getFinish";

  return "";
}

// return 0 if pass otherwise the number of the test that was failed
uint8_t runTest(Graph *pA, List *pL, int test) {
  Graph A = *pA;
  List L = *pL;
  switch(test) {

    case DG_getSize:
      {
        if (getSize(A) != 0) return 1;
        addArc(A, 54, 1);
        addArc(A, 54, 2);
        addArc(A, 54, 3);
        addArc(A, 1, 54);
        addArc(A, 1, 55);
        if (getSize(A) != 5) return 2;
        for (uint8_t i = 1; i <= 100; i++) {
          append(L, i);
        }
        DFS(A, L);
        if (getSize(A) != 5) return 3;
        addArc(A, 55, 1);
        if (getSize(A) != 6) return 4;
        return 0;
      }
    case DG_getParent:
      {
        for (uint8_t i = 1; i <= 100; i++)
          if (getParent(A, i) != NIL) return 1;
        addArc(A, 64, 4);
        addArc(A, 64, 3);
        addArc(A, 42, 2);
        addArc(A, 2, 64);
        addArc(A, 4, 2);
        addArc(A, 3, 42);
        for (uint8_t i = 1; i <= 100; i++) {
          append(L, i);
        }
        DFS(A, L);
        if (getParent(A, 100) != NIL) return 2;
        if (getParent(A, 2) != NIL) return 3;
        if (getParent(A, 42) != 3) return 4;
        if (getParent(A, 3) != 64) return 5;
        if (getParent(A, 4) != 64) return 6;
        return 0;
      }
    case DG_getDiscover:
      {
        for (uint8_t i = 1; i <= 100; i++)
          if (getDiscover(A, i) != UNDEF) return 1;
        addArc(A, 64, 4);
        addArc(A, 64, 3);
        addArc(A, 42, 2);
        addArc(A, 2, 64);
        addArc(A, 4, 2);
        addArc(A, 3, 42);
        for (uint8_t i = 1; i <= 100; i++) {
          prepend(L, i);
        }
        DFS(A, L);
        if (getDiscover(A, 100) != 1) return 2;
        if (getDiscover(A, 64) != 73) return 3;
        if (getDiscover(A, 4) != 80) return 4;
        DFS(A, L);
        if (getDiscover(A, 4) != 126) return 5;
        if (getDiscover(A, 63) != 117) return 6;
        DFS(A, L);
        if (getDiscover(A, 65) != 71) return 7;
        if (getDiscover(A, 1) != 199) return 8;
        return 0;
      }
    case DG_getFinish:
      {
        for (uint8_t i = 1; i <= 100; i++)
          if (getFinish(A, i) != UNDEF) return 1;
        addArc(A, 64, 4);
        addArc(A, 64, 3);
        addArc(A, 42, 2);
        addArc(A, 2, 64);
        addArc(A, 4, 2);
        addArc(A, 3, 42);
        for (uint8_t i = 1; i <= 100; i++) {
          prepend(L, i);
        }
        DFS(A, L);
        if (getFinish(A, 100) != 2) return 2;
        if (getFinish(A, 64) != 82) return 3;
        if (getFinish(A, 42) != 78) return 4;
        DFS(A, L);
        if (getFinish(A, 64) != 128) return 5;
        if (getFinish(A, 63) != 118) return 6;
        DFS(A, L);
        if (getFinish(A, 65) != 72) return 7;
        if (getFinish(A, 1) != 200) return 8;
        return 0;
      }
    case UG_getSize:
      {
        if (getSize(A) != 0) return 1;
        addEdge(A, 54, 1);
        addEdge(A, 54, 2);
        addEdge(A, 54, 3);
        addEdge(A, 1, 55);
        if (getSize(A) != 4) return 2;
        for (uint8_t i = 1; i <= 100; i++) {
          append(L, i);
        }
        DFS(A, L);
        if (getSize(A) != 4) return 3;
        addEdge(A, 1, 56);
        if (getSize(A) != 5) return 4;
        return 0;
      }
    case UG_getParent:
      {
        for (uint8_t i = 1; i <= 100; i++)
          if (getParent(A, i) != NIL) return 1;
        addEdge(A, 64, 4);
        addEdge(A, 64, 3);
        addEdge(A, 42, 2);
        addEdge(A, 2, 64);
        addEdge(A, 4, 2);
        addEdge(A, 3, 42);
        for (uint8_t i = 1; i <= 100; i++) {
          append(L, i);
        }
        DFS(A, L);
        if (getParent(A, 100) != NIL) return 2;
        if (getParent(A, 2) != NIL) return 3;
        if (getParent(A, 42) != 3) return 4;
        if (getParent(A, 3) != 64) return 5;
        if (getParent(A, 4) != 2) return 6;
        return 0;
      }
    case UG_getDiscover:
      {
        for (uint8_t i = 1; i <= 100; i++)
          if (getDiscover(A, i) != UNDEF) return 1;
        addEdge(A, 64, 4);
        addEdge(A, 64, 3);
        addEdge(A, 42, 2);
        addEdge(A, 2, 64);
        addEdge(A, 4, 2);
        addEdge(A, 3, 42);
        for (uint8_t i = 1; i <= 100; i++) {
          prepend(L, i);
        }
        DFS(A, L);
        if (getDiscover(A, 100) != 1) return 2;
        if (getDiscover(A, 64) != 73) return 3;
        if (getDiscover(A, 4) != 75) return 4;
        DFS(A, L);
        if (getDiscover(A, 4) != 121) return 5;
        if (getDiscover(A, 63) != 117) return 6;
        DFS(A, L);
        if (getDiscover(A, 65) != 71) return 7;
        if (getDiscover(A, 1) != 199) return 8;
        return 0;
      }
    case UG_getFinish:
      {
        for (uint8_t i = 1; i <= 100; i++)
          if (getFinish(A, i) != UNDEF) return 1;
        addEdge(A, 64, 4);
        addEdge(A, 64, 3);
        addEdge(A, 42, 2);
        addEdge(A, 2, 64);
        addEdge(A, 4, 2);
        addEdge(A, 3, 42);
        for (uint8_t i = 1; i <= 100; i++) {
          prepend(L, i);
        }
        DFS(A, L);
        if (getFinish(A, 100) != 2) return 2;
        if (getFinish(A, 64) != 82) return 3;
        if (getFinish(A, 42) != 80) return 4;
        DFS(A, L);
        if (getFinish(A, 64) != 128) return 5;
        if (getFinish(A, 63) != 118) return 6;
        DFS(A, L);
        if (getFinish(A, 65) != 72) return 7;
        if (getFinish(A, 1) != 200) return 8;
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
    printf("Usage: %s [-v]", (argc > 0 ? argv[0] : "./GraphTest"));
    exit(1);
  }

  printf("\n"); // more spacing
  if (argc == 2) printf("\n"); // consistency in verbose mode

  testsPassed = 0;
  disable_exit_handler = 0;
  atexit(exit_attempt_handler);
  signal(SIGSEGV, segfault_handler);

  for (uint8_t i = FIRST_TEST; i < NUM_TESTS; i++) {
    Graph A = newGraph(100);
    List L = newList();
    uint8_t fail_type = setjmp(test_crash);
    if (fail_type == 0) {
      testStatus = runTest(&A, &L, i);
      freeGraph(&A);
      freeList(&L);
    }
    if (argc == 2) { // it's verbose mode
      printf("Test %s %s", testName(i),
          testStatus == 0 ? "PASSED" : "FAILED");
      if (testStatus == 255) {
        printf(": due to a %s\n", fail_type == 1 ? "segfault" : fail_type == 2 ?
            "program exit" : "program interruption");
        printf("\nWARNING: Program will now stop running tests\n\n");
        break;

      } else if (testStatus == 254) {
        printf(": undefined test\n");
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

  uint8_t totalScore = (MAXSCORE - NUM_TESTS * 5) + testsPassed * 5;

  if (argc == 2) {
    if (testStatus == 255) {
      totalScore = 10;
      printf("\nYou will recieve charity points due to a program crash\n");
    } else {
      printf("\nYou passed %d out of %d tests\n", testsPassed,
          NUM_TESTS);
    }
  }
  printf("\nYou will receive %d out of %d possible points on the GraphTest\n\n",
      totalScore, MAXSCORE);

  return 0;
}
