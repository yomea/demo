import com.alibaba.cloud.nacos.parser.NacosDataParserHandler;
import java.io.IOException;
import java.util.List;
import org.springframework.core.env.PropertySource;

/**
 * @author wuzhenhong
 * @date 2024/11/13 16:20
 */
public class tt {

    public static void main(String[] args) throws IOException {
        List<PropertySource<?>> propertySources =  NacosDataParserHandler.getInstance().parseNacosData("test", "spring.cloud.config.allowOverride: true\n"
                + "spring.cloud.config.overrideNone: true",
            "yml");

        System.out.println(propertySources);
    }

}
