#include <iostream>
using namespace std;

int x;
int y;
int* ptr;
int arr[x];

int foo() {

  for (x = 0; x <= 12; x++){
    y=x;
    cout << x << " ";
    cout << y << " ";
    cout << "\n";
	cout << *((ptr) + (x+y) )<< endl ;
    y++;
  }
  return 0;
}