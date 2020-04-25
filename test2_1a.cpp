#include <iostream>
using namespace std;

  enum Months { Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Nov, Dec}; 
  enum Colors { Red, Grn, Blu, Blk, Yel, Wht, Brn, Vio, Pnk, Gry, Org};
  

int main() {
  Colors color;
  Months birthday;
  int x = birthday;
  int y = color;
  int* ptr;
  int arr[birthday];
  

  for (x= Jan; x <= Dec; x++){
    cout << x << " ";
    cout << y << " ";
    cout << "\n";
    cout << *((ptr) + (x+y) )<< endl ;
    y++;
  }
  return 0;
}
