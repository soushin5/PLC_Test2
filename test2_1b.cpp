#include <iostream>
using namespace std;

int x;
int y;

int foo() {

  for (x = 0; x <= 12; x++){
    y=x;
    cout << x << " ";
    cout << y << " ";
    cout << "\n";
    y++;
  }
  return 0;
}