package app.dao.repositories;

import app.dao.AdvConnection;
import app.entities.Person;
import app.enums.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonRepo implements Repository<Person> {

    private AdvConnection conn = AdvConnection.getInstance();

    @Override
    public boolean create(Person obj) {
        String sql = "INSERT INTO Person values(?,?,?,?,?)";
        try {
            conn.voidTransaction(() -> {
                PreparedStatement createReq = conn.prepareStatement(sql);
                createReq.setLong(1, obj.getPersonID());
                createReq.setString(2, obj.getName());
                StringBuilder roles = new StringBuilder();
                for (Role role : obj.getRoles()) roles.append(role.toString()).append("/");
                createReq.setString(3, roles.substring(0, roles.lastIndexOf("/")));
                createReq.setString(4, obj.getBirthDay().toString());
                createReq.setString(5, obj.getCountryFrom());
                return createReq.executeUpdate();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Person getById(long id) {
        String sql = "SELECT * FROM Person WHERE id = ?";
        try {
            ResultSet rs = conn.resultTransaction(() -> {
                PreparedStatement getRequest = conn.prepareStatement(sql);
                getRequest.setLong(1, id);
                return getRequest.executeQuery();
            });
            if (rs.next()) {
                Set<Role> setOfRoles = new HashSet<>();
                for (String role : rs.getString("occupation").split("/"))
                    setOfRoles.add(Role.valueOf(role));
                return new Person(rs.getLong("id"), rs.getString("name"), setOfRoles,
                        LocalDate.parse(rs.getString("dateOfBirth")), rs.getString("placeOfBirth"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(Person obj) {
        String sql = "DELETE FROM Person WHERE id = ?";
        try {
            conn.voidTransaction(() -> {
                PreparedStatement deleteReq = conn.prepareStatement(sql);
                deleteReq.setLong(1, obj.getPersonID());
                return deleteReq.executeUpdate();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(long id, Person obj) {
        String sql = "UPDATE Person SET name=?,occupation=?,dateOfBirth=?,placeOfBirth=? WHERE id=?";
        try {
            conn.voidTransaction(() -> {
                PreparedStatement updateReq = conn.prepareStatement(sql);
                updateReq.setString(1, obj.getName());
                StringBuilder roles = new StringBuilder();
                for (Role role : obj.getRoles()) roles.append(role.toString()).append("/");
                updateReq.setString(2, roles.toString());
                updateReq.setString(3, obj.getBirthDay().toString());
                updateReq.setString(4, obj.getCountryFrom());
                updateReq.setLong(5, obj.getPersonID());
                return updateReq.executeUpdate();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Person> getAll() {
        String sql = "SELECT * FROM Person";
        List<Person> res = new ArrayList<>();
        try {
            ResultSet rs = conn.resultTransaction(() -> {
                PreparedStatement getRequest = conn.prepareStatement(sql);
                return getRequest.executeQuery();
            });
            if (rs.next()) {
                do {
                    Set<Role> setOfRoles = new HashSet<>();
                    for (String role : rs.getString("occupation").split("/"))
                        setOfRoles.add(Role.valueOf(role));
                    res.add(new Person(rs.getLong("id"), rs.getString("name"), setOfRoles,
                            LocalDate.parse(rs.getString("dateOfBirth")), rs.getString("placeOfBirth")));
                } while (rs.next());
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
