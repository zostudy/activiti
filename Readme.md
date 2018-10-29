数据库
act_ge: general 通用数据表
  act_ge_bytearray 流程引擎相关的资源, 比如: 流程文件, 流程图等
    REV_ 数据版本
  act_ge_property 属性表
act_re: repository 流程定义和部署数据
  act_re_deployment 部署表
  act_re_model 
  act_re_procdef 流程定义表
act_id: identity 角色数据
  act_id_group 
  act_id_info 用户信息表, 帐号和信息
    TYPE_ 类型
      account 帐号
      userinfo 用户信息
      null 
  act_id_membership 
  act_id_user 
act_ru: runtime 运行时数据
  act_ru_deadletter_job 无法执行的工作表
  act_ru_event_subscr 事件描述表
  act_ru_execution 流程实例和执行流
  act_ru_identitylink 流程与任务关系表
  act_ru_job 一般工作表
  act_ru_suspended_job 终端工作表
  act_ru_task 任务表
  act_ru_timer_job 定时工作表
  act_ru_variable 参数表
act_hi: history 历史数据
  act_hi_actinst 
  act_hi_attachment 
  act_hi_comment 
  act_hi_detail 
  act_hi_identitylink 
  act_hi_procinst 流程实例历史表
  act_hi_taskinst 流程任务历史表
  act_hi_varinst 流程参数历史表
act_dmn: Dynamic Bpmn 动态 BPMN 规则引擎
act_evt_log: event log 事件日志数据
act_procdef_info: process define information 流程定义信息 