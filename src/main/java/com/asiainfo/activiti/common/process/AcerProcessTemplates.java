package com.asiainfo.activiti.common.process;

/**
 * 宏碁笔记本
 *
 * @author joker
 */
public class AcerProcessTemplates {
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 启动</br>
	 */
	public final static Integer PHASE_1_ID = 1;
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 */
	public final static Integer PHASE_2_ID = 2;
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 归档</br>
	 */
	public final static Integer PHASE_3_ID = 3;
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 启动</br>
	 * 环节: 开始事件</br>
	 */
	public final static Integer LINK_ID_1000 = 1000;
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 启动</br>
	 * 环节: 开始事件</br>
	 */
	public final static String LINK_NO_1000 = "StartEvent";
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 人工任务</br>
	 */
	public final static Integer LINK_ID_1001 = 1001;
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 人工任务</br>
	 */
	public final static String LINK_NO_1001 = "UserTask";
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 会签人工任务</br>
	 */
	public final static Integer LINK_ID_1002 = 1002;
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 会签人工任务</br>
	 */
	public final static String LINK_NO_1002 = "SignUserTask";
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 并行上部人工任务</br>
	 */
	public final static Integer LINK_ID_1003 = 1003;
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 并行上部人工任务</br>
	 */
	public final static String LINK_NO_1003 = "ParallelTopUserTask";
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 并行下部人工任务</br>
	 */
	public final static Integer LINK_ID_1004 = 1004;
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 并行下部人工任务</br>
	 */
	public final static String LINK_NO_1004 = "ParallelBottomUserTask";
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 最后人工任务</br>
	 */
	public final static Integer LINK_ID_1005 = 1005;
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 最后人工任务</br>
	 */
	public final static String LINK_NO_1005 = "LastUserTask";
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 并行上部会签任务</br>
	 */
	public final static Integer LINK_ID_1006 = 1006;
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 并行上部会签任务</br>
	 */
	public final static String LINK_NO_1006 = "ParallelTopSignTask";
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 其他人工任务</br>
	 */
	public final static Integer LINK_ID_1008 = 1008;
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 审批</br>
	 * 环节: 其他人工任务</br>
	 */
	public final static String LINK_NO_1008 = "OtherUserTask";
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 归档</br>
	 * 环节: 结束事件</br>
	 */
	public final static Integer LINK_ID_1007 = 1007;
	
	/**
	 * 流程: 宏碁笔记本</br>
	 * 阶段: 归档</br>
	 * 环节: 结束事件</br>
	 */
	public final static String LINK_NO_1007 = "EndEvent";
	
}