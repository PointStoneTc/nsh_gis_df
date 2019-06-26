package nsh.dengfeng.gisplan.storeGeometry.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GpStoreGeometry entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gp_store_geometry")
public class StoreGeometry implements java.io.Serializable {
    private static final long serialVersionUID = -8061284843651085195L;
    private Long id;  //
    private Long fkId;  //
    private String fkKey;  //
    private String coordinateSys;  //
    private String coordinateSysCode;  //
    private Double longitude;  //
    private Double latitude;  //
    private String province;  //
    private String geometry;  //
    private Short provinceCode;  //
    private String city;  //
    private Short cityCode;  //
    private String county;  //
    private Short countyCode;  //

    // Constructors

    /** default constructor */
    public StoreGeometry() {}

    /** minimal constructor */
    public StoreGeometry(Long fkId, String fkKey, String coordinateSys, String coordinateSysCode, Double longitude, Double latitude) {
        this.fkId = fkId;
        this.fkKey = fkKey;
        this.coordinateSys = coordinateSys;
        this.coordinateSysCode = coordinateSysCode;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /** full constructor */
    public StoreGeometry(Long fkId, String fkKey, String coordinateSys, String coordinateSysCode, Double longitude, Double latitude, String province, String geometry,
            Short provinceCode, String city, Short cityCode, String county, Short countyCode) {
        this.fkId = fkId;
        this.fkKey = fkKey;
        this.coordinateSys = coordinateSys;
        this.coordinateSysCode = coordinateSysCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.province = province;
        this.geometry = geometry;
        this.provinceCode = provinceCode;
        this.city = city;
        this.cityCode = cityCode;
        this.county = county;
        this.countyCode = countyCode;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "fk_id", nullable = false)
    public Long getFkId() {
        return this.fkId;
    }

    public void setFkId(Long fkId) {
        this.fkId = fkId;
    }

    @Column(name = "fk_key", nullable = false, length = 5)
    public String getFkKey() {
        return fkKey;
    }

    public void setFkKey(String fkKey) {
        this.fkKey = fkKey;
    }

    @Column(name = "coordinate_sys", nullable = false, length = 10)
    public String getCoordinateSys() {
        return this.coordinateSys;
    }

    public void setCoordinateSys(String coordinateSys) {
        this.coordinateSys = coordinateSys;
    }

    @Column(name = "coordinate_sys_code", nullable = false, length = 1)
    public String getCoordinateSysCode() {
        return this.coordinateSysCode;
    }

    public void setCoordinateSysCode(String coordinateSysCode) {
        this.coordinateSysCode = coordinateSysCode;
    }

    @Column(name = "longitude", nullable = false, precision = 22, scale = 0)
    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Column(name = "latitude", nullable = false, precision = 22, scale = 0)
    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Column(name = "province", length = 20)
    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "geometry")
    public String getGeometry() {
        return this.geometry;
    }

    public void setGeometry(String geometry) {
        this.geometry = geometry;
    }

    @Column(name = "province_code")
    public Short getProvinceCode() {
        return this.provinceCode;
    }

    public void setProvinceCode(Short provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Column(name = "city", length = 20)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "city_code")
    public Short getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(Short cityCode) {
        this.cityCode = cityCode;
    }

    @Column(name = "county", length = 20)
    public String getCounty() {
        return this.county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Column(name = "county_code")
    public Short getCountyCode() {
        return this.countyCode;
    }

    public void setCountyCode(Short countyCode) {
        this.countyCode = countyCode;
    }

}
