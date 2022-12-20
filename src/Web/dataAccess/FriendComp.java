package Web.dataAccess;

import java.util.Comparator;

class FriendComp implements Comparator<FriendPO> {
	public int compare(FriendPO aFriend, FriendPO bFriend) {
		//升序排列
		return aFriend.getPhone().compareTo(bFriend.getPhone());
	}
}
