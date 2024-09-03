package dao;

public class DAOFactory {
    public static ManagerDAO getManagerDAO() {
//        return JdbiDAOFactory.getManagerDAO();
        return new ManagerCollectionsDAO();
    }

    public static PropertyDAO getPropertyDAO() {
//        return JdbiDAOFactory.getPropertyDAO();
        return new PropertyCollectionsDAO();
    }

}
