package entity;

/**
 * Created by leilixia on 2015/6/7.
 *
 * relation:follow:0, followed:1, mutual follow:2
 *
 */
public class FollowRelation {
	private int id;
	private int fromUserID;
	private int toUserID;
	private int relation;  //0,1,2

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFromUserID() {
		return fromUserID;
	}

	public void setFromUserID(int fromUserID) {
		this.fromUserID = fromUserID;
	}

	public int getToUserID() {
		return toUserID;
	}

	public void setToUserID(int toUserID) {
		this.toUserID = toUserID;
	}

	public int getRelation() {
		return relation;
	}

	public void setRelation(int relation) {
		this.relation = relation;
	}
}
