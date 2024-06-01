package exportation.model.da;

import exportation.model.entity.enums.Navigation;
import exportation.model.entity.enums.PathType;
import lombok.extern.log4j.Log4j;
import exportation.model.entity.AccessPath;
import exportation.model.tools.CRUD;
import exportation.model.tools.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class AccessPathDa implements AutoCloseable, CRUD<AccessPath> {
    private final Connection connection;
    private PreparedStatement preparedStatement;

    public AccessPathDa() throws SQLException {
        connection = ConnectionProvider.getConnectionProvider().getConnection();
    }

    //save
    @Override
    public AccessPath save(AccessPath accessPath) throws Exception {
        accessPath.setId(ConnectionProvider.getConnectionProvider().getNextId("ACCESS_PATH_SEQ"));

        preparedStatement = connection.prepareStatement(
                "INSERT INTO ACCESS_PATH (ACCESS_PATH_ID, ACCESS_PATH_CITY, ACCESS_PATH_TYPE, ACCESS_PATH_DISTANCE, ACCESS_NAVIGATION) VALUES (?,?,?,?,?)"
        );
        preparedStatement.setInt(1, accessPath.getId());
        preparedStatement.setString(2, accessPath.getCity());
        preparedStatement.setString(3, String.valueOf(accessPath.getPathType()));
        preparedStatement.setFloat(4, accessPath.getDistance());
        preparedStatement.setString(5, String.valueOf(accessPath.getNavigation()));
        preparedStatement.execute();
        return accessPath;
    }

    //edit
    @Override
    public AccessPath edit(AccessPath accessPath) throws Exception {
        preparedStatement = connection.prepareStatement(
                "UPDATE ACCESS_PATH SET ACCESS_PATH_CITY=?, ACCESS_PATH_TYPE=?, ACCESS_PATH_DISTANCE=?, ACCESS_NAVIGATION=? WHERE ACCESS_PATH_ID=?"
        );
        preparedStatement.setInt(1, accessPath.getId());
        preparedStatement.setString(2, accessPath.getCity());
        preparedStatement.setString(3, String.valueOf(accessPath.getPathType()));
        preparedStatement.setFloat(4, accessPath.getDistance());
        preparedStatement.setString(5, String.valueOf(accessPath.getNavigation()));
        preparedStatement.execute();
        return accessPath;
    }

    //remove
    @Override
    public AccessPath remove(int id) throws Exception {
        preparedStatement = connection.prepareStatement(
                "DELETE FROM ACCESS_PATH WHERE ACCESS_PATH_ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        return null;
    }

    //findAll
    @Override
    public List<AccessPath> findAll() throws Exception {
        List<AccessPath> accessPathList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("SELECT * FROM ACCESS_PATH ORDER BY ACCESS_PATH_ID");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            AccessPath accessPath = AccessPath
                    .builder()
                    .id(resultSet.getInt("ACCESS_PATH_ID"))
                    .city(resultSet.getString("ACCESS_PATH_CITY"))
                    .pathType(PathType.valueOf(resultSet.getString("ACCESS_PATH_TYPE")))
                    .distance(resultSet.getFloat(resultSet.getString("ACCESS_PATH_DISTANCE")))
                    .navigation(Navigation.valueOf(resultSet.getString("ACCESS_NAVIGATION")))
                    .build();

            accessPathList.add(accessPath);
        }

        return accessPathList;
    }

    //findById
    @Override
    public AccessPath findById(int id) throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM ACCESS_PATH WHERE ACCESS_PATH_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        AccessPath accessPath = null;
        if (resultSet.next()) {
            accessPath = AccessPath
                    .builder()
                    .id(resultSet.getInt("ACCESS_PATH_ID"))
                    .city(resultSet.getString("ACCESS_PATH_CITY"))
                    .pathType(PathType.valueOf(resultSet.getString("ACCESS_PATH_TYPE")))
                    .distance(resultSet.getFloat(resultSet.getString("ACCESS_PATH_DISTANCE")))
                    .navigation(Navigation.valueOf(resultSet.getString("ACCESS_NAVIGATION")))
                    .build();
        }
        return accessPath;
    }

    //close
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
