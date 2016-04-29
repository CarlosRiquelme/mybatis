package py.pol.una.ii.pw.mapper;

import py.pol.una.ii.pw.model.Member;


public interface MemberMapper {
	
	public Member selectMemberById(int id);

	public Member getAllMembers(int id);
	
	public void insertMember(Member member);
}
