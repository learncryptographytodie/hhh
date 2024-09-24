package xmgl.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xmgl.common.core.controller.BaseController;
import xmgl.common.core.domain.AjaxResult;
import xmgl.common.core.domain.model.RegisterBody;
import xmgl.common.utils.StringUtils;
import xmgl.framework.web.service.SysRegisterService;
import xmgl.system.service.ISysConfigService;

/**
 * 注册验证
 *
 * @author ruoyi
 */
@RestController
public class SysRegisterController extends BaseController
{
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user)
    {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
    @PostMapping("/findPwdByPhone")
    public AjaxResult findPwdByPhone(@RequestBody String user)
    {
        System.out.println(user);
        String msg = "";
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

    @PostMapping("/findPwdByEmail")
    public AjaxResult findPwdByEmail(@RequestBody String user)
    {
        System.out.println(user);
        String msg = "";
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }

}
