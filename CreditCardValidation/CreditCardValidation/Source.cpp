
#include <iostream>
#include <vector>
#include <string>
#include <iomanip>

using namespace std;


bool isvalidcc(const string&);

int main()
{
	//
	// PLEASE DO NOT CHANGE main()
	//
	vector<string> cardnumbers = {
		"371449635398431", "4444444444444448", "4444424444444440", "4110144110144115",
		"4114360123456785", "4061724061724061", "5500005555555559", "5115915115915118",
		"5555555555555557", "6011016011016011", "372449635398431", "4444544444444448",
		"4444434444444440", "4110145110144115", "4124360123456785", "4062724061724061",
		"5501005555555559", "5125915115915118", "5556555555555557", "6011116011016011",
		"372449635397431", "4444544444444448", "4444434444544440", "4110145110184115",
		"4124360123457785", "4062724061724061", "5541005555555559", "5125115115915118",
		"5556551555555557", "6011316011016011"
	};

	int i;
	vector<string>::iterator itr;

	for (i = 1, itr = cardnumbers.begin(); itr != cardnumbers.end(); ++itr, i++) {
		cout << setw(2) << i << " "
			<< setw(17) << *itr
			<< ((isvalidcc(*itr)) ? " is valid" : " is not valid") << endl;
	}

	return 0;
}


bool isvalidcc(const string& cc) {
	string s = cc;
	int sum1 = 0;
	int sum2 = 0; 

	vector<int> vec(s.begin(), s.end());
	for (auto& i : vec) {
		i -= '0';
	}
	for (int i = (vec.size() - 1); i >= 0; i-=2) {
		sum1 += vec[i];
	}
	for (int i = (vec.size() -2); i >= 0; i -= 2) {
		//cout << "index "<< i << " is: " << array[i] << endl;
		if (vec[i] * 2<10) {
			sum2 += vec[i] * 2;
		}
		else {
			switch (vec[i] * 2) {
			case 10: sum2 += 1;
				break;
			case 12: sum2 += 3;
				break;
			case 14: sum2 += 5;
				break;
			case 16: sum2 += 7;
				break;
			case 18: sum2 += 9;
				break;
			}
		}
	}
	int sum = sum1 + sum2;
	if (sum % 10 == 0)
		return true;
	if (sum % 10 != 0)
		return false;
}
