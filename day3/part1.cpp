#include <iostream>
#include <fstream>
#include <vector>
#include <string>

int MAX_X;
int MAX_Y;

using namespace std;

struct Coord{
    int num;
    int x;
    int y;
};

vector<string> parse(){
    
    fstream myfile;

    myfile.open("input");
    string line;

    vector<string> v1;
    while ( getline(myfile,line) ){
      v1.push_back(line);
    }

    myfile.close();
    return v1;
}

void showarray(vector<Coord> c){
    for (auto & element : c) {
        cout << element.num << " x: " << element.x << " y: " << element.y<< endl;
    }
}

vector<Coord> getAllNumbers(vector<string> vv){
    std::vector<Coord> vc;
        
    int x=0;
    for (auto & element : vv) {
        string line = (string) element;
        int i=0;
        string number = "";
        int yp =0;
        for (int i=0; i<=line.size()-1; i++) {
            if((line[i]<='9') && (line[i]>='0')){
                if(number == "") yp = i;
                number += line[i];
            }else{
                if(number != ""){
                    struct Coord cc;
                    cc.num = atoi(number.c_str());
                    cc.x = x;
                    cc.y = yp;
                    vc.push_back(cc);
                }
                number = "";
            }
        } 
        x++;
    }
    return vc;
}

bool isOutside(int x, int y){
  if((x<0) || (x>=MAX_X)) return true;
  if((y<0) || (y>=MAX_Y)) return true;
  return false;
}

bool checksur(int x, int y, vector<string> vect ){
    if(!isOutside(x-1,y))
    if((vect[x-1][y] != '.') && ((vect[x-1][y] > '9') || (vect[x-1][y] < '0')) )return true;

    if(!isOutside(x-1,y+1))
    if((vect[x-1][y+1] != '.') && ((vect[x-1][y+1] > '9') || (vect[x-1][y+1] < '0')) )return true;

    if(!isOutside(x,y+1))
    if((vect[x][y+1] != '.') && ((vect[x][y+1] > '9') || (vect[x][y+1] < '0')) )return true;

    if(!isOutside(x+1,y+1))
    if((vect[x+1][y+1] != '.') && ((vect[x+1][y+1] > '9') || (vect[x+1][y+1] < '0')) )return true;

    if(!isOutside(x+1,y))
    if((vect[x+1][y] != '.') && ((vect[x+1][y] > '9') || (vect[x+1][y] < '0')) )return true;

    if(!isOutside(x+1,y-1))
    if((vect[x+1][y-1] != '.') && ((vect[x+1][y-1] > '9') || (vect[x+1][y-1] < '0')) )return true;

    if(!isOutside(x,y-1))
    if((vect[x][y-1] != '.') && ((vect[x][y-1] > '9') || (vect[x][y-1] < '0')) )return true;

    if(!isOutside(x-1,y-1))
    if((vect[x-1][y-1] != '.') && ((vect[x-1][y-1] > '9') || (vect[x-1][y-1] < '0')) )return true;
    return false;
}

int main() {
    std::cout << "Hello World!" << "\n";
    std::vector<string> vect = parse();
    MAX_X = vect.size();
    MAX_Y = vect[0].size();
    cout << MAX_X << ", "<< MAX_Y << endl;
    vector<Coord> numbers = getAllNumbers(vect);
    showarray(numbers);
    int sum = 0;
    cout << endl << "********" << endl;
    for(int i=0; i<=numbers.size()-1; i++){
        int r = numbers[i].y;
        for(int j=0; j<=to_string(numbers[i].num).size()-1; j++){
            if(checksur(numbers[i].x, r, vect)){
                //cout << numbers[i].num << ", ";
                sum += numbers[i].num;
                break;
            }
            r++;
        }
    }
    cout << "result: " << sum << endl;
    return 0;
}