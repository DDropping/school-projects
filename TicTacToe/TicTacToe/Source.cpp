#include <iostream>
using namespace std;

bool isWon(char, char[][3]);
bool isDraw(char[][3]);
void displayBoard(char[][3]);
void makeAMove(char[][3], char);

char board[3][3] = { { ' ', ' ', ' ' },{ ' ', ' ', ' ' },{ ' ', ' ', ' ' } };
//int mycount = 0;

int main() {
	//
	//	PLEASE DO NOT CHANGE main()
	//

	char board[3][3] =
	{ { ' ', ' ', ' ' },{ ' ', ' ', ' ' },{ ' ', ' ', ' ' } };
	displayBoard(board);

	while (true) {
		// The first player makes a move
		makeAMove(board, 'X');
		displayBoard(board);
		if (isWon('X', board)) {
			cout << "X player won" << endl;
			exit(0);
		}
		else if (isDraw(board)) {
			cout << "No winner" << endl;
			exit(0);
		}

		// The second player makes a move
		makeAMove(board, 'O');
		displayBoard(board);

		if (isWon('O', board)) {
			cout << "O player won" << endl;
			exit(0);
		}
		else if (isDraw(board)) {
			cout << "No winner" << endl;
			exit(0);
		}
	}

	return 0;
}








bool isWon(char, char[][3]) {
	if ((board[0][0] == board[0][1]) && (board[0][1] == board[0][2]) && (board[0][0] != ' '))
		return true;
	else if ((board[1][0] == board[1][1]) && (board[1][1] == board[1][2]) && (board[1][0] != ' '))
		return true;
	else if ((board[2][0] == board[2][1]) && (board[2][1] == board[2][2]) && (board[2][0] != ' '))
		return true;
	else if ((board[0][0] == board[1][0]) && (board[1][0] == board[2][0]) && (board[0][0] != ' '))
		return true;
	else if ((board[0][1] == board[1][1]) && (board[1][1] == board[2][1]) && (board[0][1] != ' '))
		return true;
	else if ((board[0][2] == board[1][2]) && (board[1][2] == board[2][2]) && (board[0][2] != ' '))
		return true;
	else if ((board[0][0] == board[1][1]) && (board[1][1] == board[2][2]) && (board[0][0] != ' '))
		return true;
	else if ((board[0][2] == board[1][1]) && (board[1][1] == board[2][0]) && (board[0][2] != ' '))
		return true;
	else
		return false;

}







bool isDraw(char[][3]) {
	int drawCount = 0;
	for (int row = 0; row<3; row++) {
		for (int column = 0; column<3; column++) {
			if (board[row][column] != ' ') {
				drawCount++;
			}
		}
	}

	if (drawCount == 9)
		return true;

	else
		return false;


}







void displayBoard(char[][3]) {

	cout << "-------------" << endl;
	cout << "| " << board[0][0] << " | " << board[0][1] << " | " << board[0][2]
		<< " |" << endl;
	cout << "-------------" << endl;
	cout << "| " << board[1][0] << " | " << board[1][1] << " | " << board[1][2]
		<< " |" << endl;
	cout << "-------------" << endl;
	cout << "| " << board[2][0] << " | " << board[2][1] << " | " << board[2][2]
		<< " |" << endl;
	cout << "-------------" << endl;
}






void makeAMove(char[][3], char player) {

	int i;
	int j;
	//char player;
	//int count = mycount;

	//if (count % 2 == 0) {
	//	player = 'X';
	//}
	//else {
	//	player = 'O';
	//}

	while (true) {
		cout << "Enter a row (0, 1, 2) for player " << player << "   : ";
		cin >> i;
		cout << "Enter a column (0, 1, 2) for player " << player << ": ";
		cin >> j;

		if (board[i][j] == ' ') {
			board[i][j] = player;
			break;
		}
		else {
			cout << "This cell is already occupied. Try a different cell" << endl;
		}
	}




	//mycount++;

}

