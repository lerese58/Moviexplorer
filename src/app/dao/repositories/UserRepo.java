package app.dao.repositories;

import app.dao.AdvConnection;
import app.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements Repository<User> {
    private AdvConnection conn = AdvConnection.getInstance();

    @Override
    public boolean create(User obj) {
        String sql = "INSERT INTO User values(?,?,?,?)";
        try {
            conn.voidTransaction(() -> {
                PreparedStatement createReq = conn.prepareStatement(sql);
                createReq.setLong(1, obj.getUserID());
                createReq.setString(2, obj.getLogin());
                createReq.setString(3, obj.getPassword());
                createReq.setString(4, obj.getRegisterDate().toString());
                return createReq.executeUpdate();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public User getById(long id) {
        String sql = "SELECT * FROM User WHERE id = ?";
        try {
            ResultSet rs = conn.resultTransaction(() -> {
                PreparedStatement getRequest = conn.prepareStatement(sql);
                getRequest.setLong(1, id);
                return getRequest.executeQuery();
            });
            if (rs.next()) {
//                String col = "SELECT title, likes, dislikes FROM UsersCollection WHERE userId=? AND NOT title=? AND NOT title=? AND NOT title=?";
//                String alb = "SELECT filmId FROM FilmInAlbum WHERE userId=? AND title=?";
//                String lws = "SELECT title, likes, dislikes FROM UsersCollection WHERE userId=? AND title=?";
//                ResultSet rsCol = conn.resultTransaction(() -> {
//                    PreparedStatement colReq = conn.prepareStatement(col);
//                    colReq.setLong(1, id);
//                    colReq.setString(2, "likedFilms");
//                    colReq.setString(3, "watchLater");
//                    colReq.setString(4, "seen");
//                    return colReq.executeQuery();
//                });
//
//                ResultSet rsLiked = conn.resultTransaction(() -> {
//                    PreparedStatement colReq = conn.prepareStatement(lws);
//                    colReq.setLong(1, id);
//                    colReq.setString(2, "likedFilms");
//                    return colReq.executeQuery();
//                });
//                ResultSet rsWatch = conn.resultTransaction(() -> {
//                    PreparedStatement colReq = conn.prepareStatement(lws);
//                    colReq.setLong(1, id);
//                    colReq.setString(2, "watchLater");
//                    return colReq.executeQuery();
//                });
//                ResultSet rsSeen = conn.resultTransaction(() -> {
//                    PreparedStatement colReq = conn.prepareStatement(lws);
//                    colReq.setLong(1, id);
//                    colReq.setString(2, "seen");
//                    return colReq.executeQuery();
//                });
//
//                if (rsLiked.next()) {
//                    ResultSet likedId = conn.resultTransaction(() -> {
//                        PreparedStatement s = conn.prepareStatement(alb);
//                        s.setLong(1, id);
//                        s.setString(2, rsLiked.getString("title"));
//                        return s.executeQuery();
//                    });
//                }
//                if (rsWatch.next()) {
//                    ResultSet watchId = conn.resultTransaction(() -> {
//                        PreparedStatement s = conn.prepareStatement(alb);
//                        s.setLong(1, id);
//                        s.setString(2, rsWatch.getString("title"));
//                        return s.executeQuery();
//                    });
//                }
//                if (rsSeen.next()) {
//                    ResultSet seenId = conn.resultTransaction(() -> {
//                        PreparedStatement s = conn.prepareStatement(alb);
//                        s.setLong(1, id);
//                        s.setString(2, rsSeen.getString("title"));
//                        return s.executeQuery();
//                    });
//                }

//                ResultSet rsAlb = conn.resultTransaction(() -> {
//                    PreparedStatement albReq = conn.prepareStatement(alb);
//                    albReq.setLong(1, id);
//                    return albReq.executeQuery();
//                });
//                FilmCollection l = new FilmCollection(id, "likedFilms");
                return new User(rs.getLong("id"),
                        rs.getString("login"),
                        rs.getString("password"),
                        LocalDate.parse(rs.getString("registerDate")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(User obj) {
        String sql = "DELETE FROM User WHERE id = ?";
        try {
            conn.voidTransaction(() -> {
                PreparedStatement deleteReq = conn.prepareStatement(sql);
                deleteReq.setLong(1, obj.getUserID());
                return deleteReq.executeUpdate();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(long id, User obj) {
        String sql = "UPDATE User SET login=?,password=?,registerDate=? WHERE id=?";
        try {
            conn.voidTransaction(() -> {
                PreparedStatement updateReq = conn.prepareStatement(sql);
                updateReq.setString(1, obj.getLogin());
                updateReq.setString(3, obj.getPassword());
                updateReq.setString(4, obj.getRegisterDate().toString());
                return updateReq.executeUpdate();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM User";
        List<User> res = new ArrayList<>();
        try {
            ResultSet rs = conn.resultTransaction(() -> {
                PreparedStatement getRequest = conn.prepareStatement(sql);
                return getRequest.executeQuery();
            });
            if (rs.next()) {
                do {
                    res.add(new User(rs.getLong("id"),
                            rs.getString("login"),
                            rs.getString("password"),
                            LocalDate.parse(rs.getString("registerDate"))));
                } while (rs.next());
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
