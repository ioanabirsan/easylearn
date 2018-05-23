package ro.infoiasi.ip.easylearn.user.repository.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.infoiasi.ip.easylearn.user.model.User;
import ro.infoiasi.ip.easylearn.user.repository.api.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.*;



@Repository
public class SqlUserRepository implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean updateData(User user) {
    	try
    	{
    		String query = "UPDATE users set name=?, firstName=?, password=? where email='" + user.getEmail() + "' OR id='" + user.getId() + "'" ;
            Object[] params = new Object[] {user.getName() , user.getFirstName(), user.getPassword() };
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
            jdbcTemplate.update(query, params, types);

            return true;
    	}
    	catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    		return false;
    	}
    }

    @Override
    public User findById(Long id) {
        List<User> users= jdbcTemplate.query("SELECT * FROM users where id='"+id+"'", new BeanPropertyRowMapper<>(User.class));
        if(users.size()>=1)
            return users.get(0);
        else return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }


    @Override
    public Integer getTotalUsers(){
        Integer total = 0;

       /* if(jdbcTemplate == null){
            return -10000;
        }*/

        try{
            String query = "SELECT COUNT(*) FROM users";
            total = jdbcTemplate.queryForObject(query, Integer.class);
        }catch (NullPointerException e){
            return -1;
       }

        return total;
    }

    @Override
    public boolean register(User user){
        if(isAvailableEmail(user.getEmail())){
            String query = "INSERT INTO users (name, firstName, email, password, secretAnswer, secretPassword) VALUES (?,?,?,?,?,?)";
            Object[] params = new Object[] {user.getName() , user.getFirstName(), user.getEmail(),user.getPassword(), user.getSecretAnswer(), user.getSecretPassword() };
            int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

            int row = jdbcTemplate.update(query, params, types);

            return true;
        }
        return false;
    }

    public boolean isAvailableEmail(String email){
        String query = "SELECT COUNT(*) FROM users WHERE email LIKE '" + email + "'";
        int total = jdbcTemplate.queryForObject(query, Integer.class);

       if(total == 0){
           return true;
       }else{
           return false;
       }
    }

    // know this works fine, dont change
    @Override
    public Long getLastId()
    {
    	Long id = jdbcTemplate.queryForObject("SELECT MAX(id) FROM users", Long.class);

        return id;
    }
    
    @Override
    public boolean login(String email, String password)
    {
    	String query = "SELECT COUNT(*) FROM users WHERE email='" + email + "' AND password='" + password + "'";
        int total = jdbcTemplate.queryForObject(query, Integer.class);
        
    	return total==1;
    }
    
    @Override
    public int getScore(Long userId)
    {
    	int score=0;
    	
    	try
    	{
            String query = "select max(score) from submissions group by problemId, userId having userId = '" + userId + "'";
            List<Integer> score_list = jdbcTemplate.queryForList(query, Integer.class);
            
            for(int i=0;i<score_list.size();i++)
            	score += score_list.get(i);
        }
    	catch (NullPointerException e)
    	{
        	System.out.println(e.getMessage());
    	}

        return score;
    }

    @Override
    public boolean delete(Long id)
    {
    	try
    	{
            String query = "DELETE FROM USERS where id='" + id + "'";
            jdbcTemplate.update(query);
            
            return true;
        }
    	catch (NullPointerException e)
    	{
        	System.out.println(e.getMessage());
        	return false;
    	}
    }
}


