package springbootskeleton.datasource.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "user")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class User implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY , generator = "IdOrGenerated")
    @GenericGenerator(name="IdOrGenerated",
            strategy="springbootskeleton.datasource.model.generators.UserIdGenerator")
    @Column(nullable = false)
	private Long id;
	
	@Column(name = "mobile_no")
	private Long mobileNo;
	
	@Column(name = "scope_cust_id")
	private String scopeCustId;
	
	@Column(name = "scope_id")
	private Long scopeId;
	
	@Column(name = "source_channel_id")
	private Long sourceChannelId;
	
	@Column(name = "default_vpa")
	private String defaultVpa;
	
	private String name;
	
	@Column(name = "active_device_id")
	private String activeDeviceId;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "comments")
	private String comments;

	@Column(name = "extended_info")
	private String extendedInfo;
	
	@Column(name = "created_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name = "updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;
	
}
