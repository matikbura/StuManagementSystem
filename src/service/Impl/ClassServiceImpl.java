package service.Impl;

import dao.ClassIDao;
import dao.Impl.ClassDaoImpl;
import domain.Classtbl;
import exception.DaoException;
import exception.ServiceException;
import service.ClassIService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClassServiceImpl implements ClassIService {
    private ClassIDao classIDao =new ClassDaoImpl();
    @Override
    public void addClasstbl(Classtbl classtbl) throws ServiceException, ServiceException {
        try{
            classIDao.addClass(classtbl);
        }catch(DaoException e){
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public void delClasstbl(String classNo) throws ServiceException {
        try{
            classIDao.delClass(classNo);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public void updateClasstbl(Classtbl classtbl) throws ServiceException {
        try{
            classIDao.updateClass(classtbl);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
    }

    @Override
    public Classtbl findClasstblByClassNo(String classNo) throws ServiceException {
        Classtbl classtbl=null;
        try{
            classtbl = classIDao.findClassByClasstblNo(classNo);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return classtbl;
    }

    @Override
    public Map findAllClasstbl(int curPage) throws ServiceException {
        Map map=null;
        try{
            map = classIDao.findAllClass(curPage);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return map;
    }

    @Override
    public List findAllClasstbl() throws ServiceException {
        List list=new ArrayList();
        try{
            list = classIDao.findAllClass();
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public List findAllClasstblByMostCon(String classNo, String className, String college) throws ServiceException {
        List list = null;
        try{
            list = classIDao.findAllClassByMostCon(classNo,className,college);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return list;
    }

    @Override
    public Map findAllClasstblByMostCon(String classNo, String className, String college, int curPage) throws ServiceException {
        Map map  = null;
        try{
            map = classIDao.findAllClassByMostCon(classNo,className,college,curPage);
        }catch (DaoException e) {
            e.printStackTrace();
            throw new DaoException(e.getMessage(),e);
        }
        return map;
    }
}
