package io.github.angrylid.mall.service;

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.github.angrylid.mall.dto.request.QualificationDto;
import io.github.angrylid.mall.dto.response.AccountInformation;
import io.github.angrylid.mall.entity.HandleProcedure;
import io.github.angrylid.mall.entity.RoleType;
import io.github.angrylid.mall.generated.entity.Qualification;
import io.github.angrylid.mall.generated.entity.Relation;
import io.github.angrylid.mall.generated.entity.Student;
import io.github.angrylid.mall.generated.entity.User;
import io.github.angrylid.mall.generated.mapper.QualificationMapper;
import io.github.angrylid.mall.generated.mapper.RelationMapper;
import io.github.angrylid.mall.generated.mapper.StudentMapper;
import io.github.angrylid.mall.generated.mapper.UserMapper;
import io.github.angrylid.mall.jwt.JwtUtil;
import io.github.angrylid.mall.utils.Minio;

@Service("userService")
public class UserService {

    private JwtUtil jwtUtil;
    private Minio minio;
    private QualificationMapper qualificationMapper;
    private RelationMapper relationMapper;
    private StudentMapper studentMapper;
    private UserMapper userMapper;

    @Autowired
    public UserService(JwtUtil jwtUtil, Minio minio, QualificationMapper qualificationMapper,
            RelationMapper relationMapper, StudentMapper studentMapper, UserMapper userMapper) {
        this.jwtUtil = jwtUtil;
        this.minio = minio;
        this.qualificationMapper = qualificationMapper;
        this.relationMapper = relationMapper;
        this.studentMapper = studentMapper;
        this.userMapper = userMapper;
    }

    /**
     * 管理员 - 获取所有用户
     *
     * @return 所有用户
     */
    public List<User> selectUsers() {
        Page<User> page = new Page<>(1, 50);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("created_at");
        Page<User> users = userMapper.selectPage(page, wrapper);
        return users.getRecords();
    }

    /**
     * 管理员 - 更新用户状态(启用/停用)
     * 
     * @param id         用户ID
     * @param authStatus
     * @return
     */
    public Integer updateUserStatus(Integer id, Integer authStatus) {
        User user = new User();
        user.setId(id);
        user.setAuthStatus(authStatus);
        int result = userMapper.updateById(user);
        return result;
    }

    /**
     * 管理员 - 通过电话号码检索用户
     * 
     * @param telephone
     * @return
     */
    public User selectUserByTelephone(String telephone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("telephone", telephone);
        User user = userMapper.selectOne(wrapper);
        return user;
    }

    /**
     * 客户端 - 更新 用户名
     * 
     * @param id   用户ID
     * @param name 用户名
     */
    public Integer updateUserName(Integer id, String name) throws SQLException {
        User user = new User();
        user.setId(id);
        user.setNickname(name);
        try {
            return userMapper.updateById(user);
        } catch (Exception ex) {
            throw new SQLException(ex);
        }
    }

    /**
     * 根据电话号码获取用户名
     * 
     * @param telephone
     * @return
     */
    public String selectNameByTelephone(String telephone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone", telephone);
        return userMapper.selectOne(queryWrapper).getNickname();
    }

    /**
     * 依据用户信息查找学生信息
     * 
     * @param id 用户ID
     * @return 关联的学生信息
     */
    public Student selectStudentInfo(Integer id) {
        User user = userMapper.selectById(id);
        Student student = studentMapper.selectById(user.getStudentId());
        return student;
    }

    /**
     * 依据用户信息查找商铺信息
     * 
     * @param id 用户ID
     * @return 关联的商铺信息
     */
    public Qualification selectMerchantInfo(Integer id) {
        User user = userMapper.selectById(id);
        Qualification qualification = qualificationMapper.selectById(user.getMerchantId());
        return qualification;
    }

    /**
     * 提交资质认证工单
     * 
     * @param dto       接受的表单数据
     * @param applicant 解码token的申请人id
     * @throws IllegalAccessException
     * @throws IOException
     */
    public void qualificationRequset(QualificationDto dto, Integer applicant)
            throws IllegalAccessException, IOException {
        var entity = new Qualification();
        entity.setEnterpriseName(dto.getEnterpriseName());
        entity.setEnterpriseType(dto.getEnterpriseType());
        entity.setApplicantId(applicant);

        for (MultipartFile file = dto.getImage0(); file != null; file = null) {
            String name = minio.upload(file);
            entity.setImage0(name);
        }
        for (MultipartFile file = dto.getImage1(); file != null; file = null) {
            String name = minio.upload(file);
            entity.setImage1(name);
        }
        for (MultipartFile file = dto.getImage2(); file != null; file = null) {
            String name = minio.upload(file);
            entity.setImage2(name);
        }
        for (MultipartFile file = dto.getImage3(); file != null; file = null) {
            String name = minio.upload(file);
            entity.setImage3(name);
        }
        for (MultipartFile file = dto.getImage4(); file != null; file = null) {
            String name = minio.upload(file);
            entity.setImage4(name);
        }
        for (MultipartFile file = dto.getImage5(); file != null; file = null) {
            String name = minio.upload(file);
            entity.setImage5(name);
        }

        qualificationMapper.insert(entity);

    }

    /**
     * 签发Token
     * 
     * @param telephone
     * @param password
     * @return JWT
     */
    public String generateToken(String telephone, String password) {
        try {
            User user = userMapper
                    .selectOne(new QueryWrapper<User>().eq("telephone", telephone).eq("passwd", password));
            if (user != null) {
                return jwtUtil.sign(telephone, password);
            } else {
                throw new IllegalArgumentException();
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("账号或密码错误");
        }

    }

    public String addUser(String telephone, String password, String nickname) {
        User user = new User();
        user.setTelephone(telephone);
        user.setPasswd(password);
        user.setNickname(nickname);
        user.setRoleType(RoleType.UNKNOWN.getValue());
        try {
            userMapper.insert(user);
        } catch (DuplicateKeyException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("该手机号已被注册");
        }
        return "Succeed!";
    }

    public User getUserByTel(String telephone) {
        User user = new User();
        try {
            user = userMapper.selectOne(new QueryWrapper<User>().eq("telephone", telephone));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return user;
    }

    public AccountInformation selectAccountInfo(Integer id) {
        AccountInformation infomation = new AccountInformation();

        try {
            Long following = relationMapper
                    .selectCount(new QueryWrapper<Relation>().eq("user_id", id).eq("is_deleted", 0));
            Long followed = relationMapper
                    .selectCount(new QueryWrapper<Relation>().eq("follower_id", id).eq("is_deleted", 0));
            infomation.setFollowing(following.intValue());
            infomation.setFollowed(followed.intValue());

            User user = userMapper.selectById(id);
            infomation.setName(user.getNickname());
            infomation.setTelephone(user.getTelephone());

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return infomation;
    }

    public boolean verifyJwt(String token) {
        return jwtUtil.verify(token);
    }

    /**
     * @param uid user table's id column
     * @return Infected row, value is 1 unless error occurs
     */
    public Integer permitStudent(Integer uid) {
        User user = new User();
        user.setId(uid);
        user.setRoleType("student_verified");
        return userMapper.updateById(user);
    }

    /**
     * 获取用户的认证信息
     * 
     * @param uid user table's id column
     * @return Infected row, value is 1 unless error occurs
     */
    public Map<String, Object> getUserStatus(Integer id) {
        User user = userMapper.selectById(id);

        Map<String, Object> map = new HashMap<>();
        Integer studentId = user.getStudentId();
        Integer merchantId = user.getMerchantId();

        if (studentId != null) {
            Student student = studentMapper.selectById(studentId);
            map.put("student", student);

        } else {
            map.put("student", null);
        }
        if (merchantId != null) {
            Qualification qualification = qualificationMapper.selectById(merchantId);
            if (qualification.getCurrentStatus().equals(HandleProcedure.APPROVED.getValue())) {
                map.put("qualification", qualification);
            } else {
                map.put("qualification", null);
            }

        } else {
            map.put("qualification", null);
        }

        return map;
    }

    public QualificationDto qualStatus(Integer id) {
        var entities = qualificationMapper.selectByMap(ofEntries(entry("applicant_id", id)));
        if (entities.size() < 1) {
            return null;
        }
        var entity = entities.get(entities.size() - 1);

        var ret = new QualificationDto();
        ret.setEnterpriseName(entity.getEnterpriseName());
        ret.setEnterpriseType(entity.getEnterpriseType());
        ret.setCurrentStatus(entity.getCurrentStatus());
        ret.setCreatedAt(entity.getCreatedAt());

        return ret;
    }
}
