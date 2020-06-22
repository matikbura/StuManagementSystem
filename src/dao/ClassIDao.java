package dao;

import domain.Classtbl;

import java.util.List;
import java.util.Map;

public interface ClassIDao {

	void addClass(Classtbl classtbl);

	void delClass(String classNo);

	void updateClass(Classtbl classtbl);

	Classtbl findClassByClasstblNo(String classNo);

	Map findAllClass(int curPage);

	List findAllClass();

	List findAllClassByMostCon(String classNo, String className,String college);

	Map findAllClassByMostCon(String LoginName, String name,String college, int curPage);
}
