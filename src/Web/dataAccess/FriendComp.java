package Web.dataAccess;

import java.util.Comparator;

class FriendComp implements Comparator<FriendPO> {
	public int compare(FriendPO aFriend, FriendPO bFriend) {
		//εεΊζε
		return aFriend.getPhone().compareTo(bFriend.getPhone());
	}
}
