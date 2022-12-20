package Web.dataAccess;

import java.util.Iterator;
import java.util.TreeSet;

public class FriendSet extends TreeSet<FriendPO> {

	public FriendSet() {
		super(new FriendComp());
	}

	public String[][] list() {
		String list[][] = new String[this.size()][3];
		Iterator<FriendPO> itr = this.iterator();
		int i = 0;
		while (itr.hasNext()) {
			FriendPO friend = itr.next();
			list[i][0] = friend.getName();
			list[i][1] = friend.getPhone();
			list[i][2] = friend.getEmail();
			i++;
		}
		return list;
	}
}
