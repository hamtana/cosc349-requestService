package dao;

public class DAOFactory {
    public static ManagerDAO getManagerDAO() {
        return JdbiDAOFactory.getManagerDAO();
    }

    public static PropertyDAO getPropertyDAO() {
        return JdbiDAOFactory.getPropertyDAO();
    }
}
