package service;

import domain.Classtbl;
import exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface ClassIService {

    void addClasstbl(Classtbl classtbl) throws ServiceException, ServiceException;

    void delClasstbl(String classNo) throws ServiceException;

    void updateClasstbl(Classtbl classtbl) throws ServiceException;

    Classtbl findClasstblByClassNo(String classNo) throws ServiceException;

    Map findAllClasstbl(int curPage) throws ServiceException;

    List findAllClasstbl() throws ServiceException;

    List findAllClasstblByMostCon(String classNo, String className,String college) throws ServiceException;

    Map findAllClasstblByMostCon(String classNo, String className,String college, int curPage) throws ServiceException;
}
