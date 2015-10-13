package interviews;

public class Pinterest_Nim_Game {
	/**
	 * Two players play a game. In this game, there are two piles of identical
	 * stones. The number of stones in first pile is A and the number of stones
	 * in second pile is B. There is also a positive integer constant K which is
	 * a parameter to the game. It stays fixed throughout the game. The players
	 * alternate taking turns. When a player's turn comes, that player first
	 * selects a pile and then picks any number of stones from that pile - he
	 * has to pick at least 1 stone and cannot pick more than K stones. The
	 * person who picks the last stone loses. Write a method to determine if
	 * player 1 has a winning strategy given A, B & K - assume both players play
	 * optimally.
	 */
	// 简单地说，就是将两堆石头的块数化为二进制，再进行 “异或运算”
	// 然后，先手一方只要保持三个数进行“异或”后结果是0就可以获胜，而对于两个数初始异或结果为0的情况，则是后手获胜 。
	// 只要得出初始异或结果为0的话让player 1选后手 非0的话选先手就可以了

	// leetcode
	/**
	 * You are playing the following Nim Game with your friend: There is a heap
	 * of stones on the table, each time one of you take turns to remove 1 to 3
	 * stones. The one who removes the last stone will be the winner. You will
	 * take the first turn to remove the stones.
	 * 
	 * Both of you are very clever and have optimal strategies for the game.
	 * Write a function to determine whether you can win the game given the
	 * number of stones in the heap.
	 * 
	 * For example, if there are 4 stones in the heap, then you will never win
	 * the game: no matter 1, 2, or 3 stones you remove, the last stone will
	 * always be removed by your friend.
	 */
	public boolean canWinNim(int n) {
		if (n % 4 == 0)
			return false;
		return true;
	}
}
