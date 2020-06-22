package utils;

import java.sql.ResultSet;
import java.sql.SQLException;


public class Page {
	private int pageSize;
	private int pageCount;
	private int curPage;
	public void setPageSize(int size){
	    pageSize=size;
	}
	public int getPageSize(){
	    return pageSize;
	}

	public void setPageCount(ResultSet rs){
		int lastrow;
		try{
			rs.last();
			lastrow=rs.getRow();
		    if(lastrow % pageSize==0){
		    	pageCount=lastrow/pageSize;
		    }else{
		    	pageCount=lastrow/pageSize+1;
		    }
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public int getPageCount(){
	  return pageCount;
	}

	public ResultSet setRs(ResultSet rs){
		try{
			rs.absolute((curPage-1)*pageSize+1);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}

	public void setCurPage(int row){
		if (row<=1)
			curPage=1;
		else if(row>=pageCount){
			curPage=pageCount;
		}else{
			curPage=row;
		}
	}

	public int getCurPage(){
		return curPage;
	}
}


