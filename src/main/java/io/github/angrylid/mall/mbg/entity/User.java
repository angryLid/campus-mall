package io.github.angrylid.mall.mbg.entity;

import java.util.Date;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.id
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.nikename
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    private String nikename;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.telephone
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    private String telephone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.passwd
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    private String passwd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.gender
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    private String gender;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.role
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    private String role;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.date_entry
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    private Date dateEntry;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.position
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    private String position;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user.is_admin
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    private Byte isAdmin;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.id
     *
     * @return the value of user.id
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.id
     *
     * @param id the value for user.id
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.nikename
     *
     * @return the value of user.nikename
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public String getNikename() {
        return nikename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.nikename
     *
     * @param nikename the value for user.nikename
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public void setNikename(String nikename) {
        this.nikename = nikename == null ? null : nikename.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.telephone
     *
     * @return the value of user.telephone
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.telephone
     *
     * @param telephone the value for user.telephone
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.passwd
     *
     * @return the value of user.passwd
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.passwd
     *
     * @param passwd the value for user.passwd
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.gender
     *
     * @return the value of user.gender
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public String getGender() {
        return gender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.gender
     *
     * @param gender the value for user.gender
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.role
     *
     * @return the value of user.role
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.role
     *
     * @param role the value for user.role
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.date_entry
     *
     * @return the value of user.date_entry
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public Date getDateEntry() {
        return dateEntry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.date_entry
     *
     * @param dateEntry the value for user.date_entry
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public void setDateEntry(Date dateEntry) {
        this.dateEntry = dateEntry;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.position
     *
     * @return the value of user.position
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public String getPosition() {
        return position;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.position
     *
     * @param position the value for user.position
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user.is_admin
     *
     * @return the value of user.is_admin
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public Byte getIsAdmin() {
        return isAdmin;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user.is_admin
     *
     * @param isAdmin the value for user.is_admin
     *
     * @mbg.generated Tue Jan 04 14:41:39 CST 2022
     */
    public void setIsAdmin(Byte isAdmin) {
        this.isAdmin = isAdmin;
    }
}