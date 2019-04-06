#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <sstream>
#include <map>
#include <list>
#include <algorithm>

using namespace std;
void replaceAll(string &s, const string &search, const string &replace);
string replaceWordSearch(string word);

int main() {
	string line;
	ifstream file("Data.CS.SFSU.txt");
	map<string, vector<string>> mymap;

	if (file.is_open()) {
		cout << "! Opening data file... ./Data.CS.SFSU.txt" << endl;
		cout << "! Loading data..." << endl;

		while (getline(file, line)) {
			istringstream ss(line);
			string token;
			vector<string> myvector;
			vector<string> newvector;
			vector<string> finalvector;

			//breaks each line into tokens at each occurance of '|'
			while (getline(ss, token, '|')) {
				myvector.push_back(token);
			}

			//gets rid of first entry in vector(name) and makes newvector
			for (int i = 1; i < myvector.size(); i++) {
				newvector.push_back(myvector[i]);
			}

			//the name key value 
			string tempkey = myvector[0];

			//edit the newvector here to match format of desired output and put into new finalvector
			for (int i = 0; i < newvector.size(); i++) {
				string temp = newvector[i];
				replaceAll(temp, "=>", " ");
				replaceAll(temp, "noun  ", "[noun] : ");
				replaceAll(temp, "adjective  ", "[adjective] : ");
				replaceAll(temp, "verb  ", "[verb] : ");
				finalvector.push_back(temp);	
			}

			//place all entries into map of with key=word and value=vector<string> of definitions
			mymap.insert(make_pair(tempkey, finalvector));
		}

		//close file and output closed file alert
		file.close();
		cout << "! Loading completed..." << endl;
		cout << "! Closing data file... ./Data.CS.SFSU.txt" << endl;
	}

	//welcome message
	cout << endl;
	cout << "----- DICTIONARY 340 C++----- " << endl;
	cout << endl;

	//creates a vector of key values to search if the userinput is valid (later in program)
	vector<string> keyWords;
	for (map<string, vector<string>>::iterator it = mymap.begin(); it != mymap.end(); it++) {
		keyWords.push_back(it->first);
	}


	//gets user input
	while (true) {
		cout << "Search: ";
		string input;
		getline(cin, input);

		//if nothing is entered, output error
		if (input == "") {
			cout << "        |" << endl;
			cout << "         <Please enter a search key (and a part of speech).>" << endl;
			cout << "        |" << endl;
		}
		else {
			//makes userinput lowercase to match the vector of keyvalues for validation
			for (int i = 0; i < input.length(); i++) {
				input[i] = tolower(input[i]);
			}

			istringstream s2(input);
			vector<string> v;
			string tmp;

			//puts user input into vector
			while (s2 >> tmp) {
				v.push_back(tmp);
			}


			string wordSearch = v[0];
			wordSearch = replaceWordSearch(wordSearch);

			//proccesses user input **********************************************************************


			//if !Q entered, exit program
			if ((v[0] == "!Q") || (v[0] == "!q")) {
				cout << endl;
				cout << "----- THANK YOU----- " << endl;
				break;
			}
			else {

				cout << "        |" << endl;

				//if nothing is entered, output error message
				if (v.size() == 0) {
					cout << "         <Please enter a search key (and a part of speech).>" << endl;

				}

				//if one word is entered, output definitions or error message
				if (v.size() == 1) {
					if (find(keyWords.begin(), keyWords.end(), v[0]) != keyWords.end()) {
						//iterate through map and find keyword, print all values in vector
						for (auto map_iter = mymap.cbegin(); map_iter != mymap.cend(); ++map_iter) {
							if (v[0] == map_iter->first) {
								for (auto vec_iter = map_iter->second.cbegin(); vec_iter != map_iter->second.cend(); ++vec_iter)
									std::cout << "         " << wordSearch << " " << *vec_iter << endl;


							}
						}
					}
					else {
						cout << "         <Not found.>" << endl;

					}
				}

				//if two or more words are entered, output definition or error message
				if (v.size() == 2) {
					if ((v[1] != "noun") && (v[1] != "adjective") && (v[1] != "verb")) {
						cout << "         <2nd argument must be a part of speech.>" << endl;

					}
					else {
						if (find(keyWords.begin(), keyWords.end(), v[0]) != keyWords.end()) {
							//cout << "return vector >> if pos if found return definition" << endl;



							for (auto map_iter = mymap.cbegin(); map_iter != mymap.cend(); ++map_iter) {
								if (v[0] == map_iter->first) {
									if ((v[1] == "noun") || (v[1] == "adjective") || (v[1] == "verb")) {
										int count = 0;
										for (auto vec_iter = map_iter->second.cbegin(); vec_iter != map_iter->second.cend(); ++vec_iter) {
											string str1 = *vec_iter;
											string str2 = v[1];
											if (str1.find(str2) != string::npos) {
												std::cout << "         " << wordSearch << " " << *vec_iter << endl;
												count++;
											}
										}
										if (count == 0) {
											cout << "         <Not found.>" << endl;

										}
									}
									else {
										cout << "         <Not found.>" << endl;

									}
								}
							}
						}
						else {
							cout << "         <Not found.>" << endl;

						}
					}
				}
				if (v.size() > 2) {
					cout << "         <Please enter a search key (and a part of speech).>" << endl;

				}
				cout << "        |" << endl;
			}
		}
	}
	return 0;
}

void replaceAll(string &s, const string &search, const string &replace) {
	for (size_t pos = 0; ; pos += replace.length()) {
		// Locate the substring to replace
		pos = s.find(search, pos);
		if (pos == string::npos) break;
		// Replace by erasing and inserting
		s.erase(pos, search.length());
		s.insert(pos, replace);
	}
}


string replaceWordSearch(string word) {
	if (word == "book")
		return "Book";
	if (word == "bookable")
		return "Bookable";
	if (word == "bookbinder")
		return "Bookbinder";
	if (word == "bookcase")
		return "Bookcase";
	if (word == "csc210")
		return "CSC210";
	if (word == "csc220")
		return "CSC220";
	if (word == "csc340")
		return "CSC340";
	else
		return word;
}