#include <iostream>
#include <algorithm> 
#include <chrono> 
using namespace std;
using namespace std::chrono;

int statArray() {       
  static int x[5000];
  return 0;
}
int stacArray(){
  int x[5000];
  return 0;
}
int heapArray(){
  int *array = new int[5000];
  delete [] array;
  return 0;
}
int statTest(){
  auto start = high_resolution_clock::now(); 
  for(int i = 0; i < 100000; i++){
    statArray();
  }
  auto stop = high_resolution_clock::now(); 
  auto duration = duration_cast<microseconds>(stop - start); 
  cout << "Time taken by Static Array: "
         << duration.count() << " microseconds" << endl;
  return 0;
}
int stacTest(){
  auto start = high_resolution_clock::now(); 
  for(int i = 0; i < 100000; i++){
    stacArray();
  }
  auto stop = high_resolution_clock::now(); 
  auto duration = duration_cast<microseconds>(stop - start); 
  cout << "Time taken by Fixed Stack-Dynamic Array: "
         << duration.count() << " microseconds" << endl;
  return 0;
}
int heapTest(){
  auto start = high_resolution_clock::now(); 
  for(int i = 0; i < 100000; i++){
    heapArray();
  }
  auto stop = high_resolution_clock::now(); 
  auto duration = duration_cast<microseconds>(stop - start); 
  cout << "Time taken by Fixed Heap-Dynamic Array: "
         << duration.count() << " microseconds" << endl;
  return 0;
}
int main(){
  statTest();
  stacTest();
  heapTest();
  return 0;
}