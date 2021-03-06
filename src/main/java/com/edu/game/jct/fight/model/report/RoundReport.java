package com.edu.game.jct.fight.model.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edu.game.Player;
import com.edu.game.jct.fight.model.BattleResult;
import com.edu.game.jct.fight.model.FighterInfo;
import com.edu.game.jct.fight.model.UnitInfo;
import com.edu.game.jct.fight.service.config.BattleType;
import com.edu.game.jct.fight.service.core.Unit;

/**
 * 回合战报
 * @author Frank
 */
public class RoundReport {

	/** 第几回合 */
	private int round;
	/** 回合开始战报 */
	private List<StartReport> starts;
	/** 大招战报(回合开始时触发) */
	private List<UltimateReport> ultimates;
	/** 技能行动战报(每个战斗单元的行动) */
	private List<SkillReport> skills = new ArrayList<SkillReport>();
	/** 攻击方援军上阵信息 */
	private FighterInfo attackerRelief;
	/** 防守方援军上阵信息 */
	private FighterInfo defenderRelief;
	/** 回合结束战报 */
	private List<EndReport> ends;
	/** 手操角色的技能冷却信息 */
	private List<CdInfo> cdInfos;
	/** 战斗结果 */
	private BattleResult battleResult;
	// 玩法的附加信息部分
	/** 战斗类型 */
	private BattleType battleType;
	/** 附加信息(玩家标识:附加内容) */
	private Map<Long, Object> additions;
	/** 需要刷新的战斗单元 */
	private List<UnitInfo> refreshs;
	// 调试信息
	/** 各活着的战斗单元的调试信息 */
	private List<DebugInfo> debugs;

	// 逻辑方法

	/**
	 * 添加玩家战斗后的附加信息
	 * @param player
	 * @param addition
	 */
	public void addAdditions(Player player, Object addition) {
		if (additions == null) {
			additions = new HashMap<Long, Object>();
		}
		additions.put(player.getId(), addition);
	}

	/** 添加需要刷新的战斗单元 */
	public void addRefresh(Unit unit) {
		if (refreshs == null) {
			refreshs = new ArrayList<UnitInfo>(1);
		}
		refreshs.add(UnitInfo.valueOf(unit));
	}

	/** 添加大招战报 */
	public void addUltimate(UltimateReport report) {
		if (ultimates == null) {
			ultimates = new ArrayList<UltimateReport>();
		}
		ultimates.add(report);
	}

	/** 添加行动战报 */
	public void addAction(SkillReport report) {
		skills.add(report);
	}

	/** 添加攻击方援军上阵信息 */
	public void addAttackerRelief(FighterInfo relief) {
		this.attackerRelief = relief;
	}

	/** 添加防守方援军上阵信息 */
	public void addDefenderRelief(FighterInfo relief) {
		this.defenderRelief = relief;
	}

	/** 添加技能冷却信息 */
	public void addCdInfo(Collection<CdInfo> cdInfos) {
		if (this.cdInfos == null) {
			this.cdInfos = new ArrayList<CdInfo>(1);
		}
		this.cdInfos.addAll(cdInfos);
	}

	/** 添加回合结束战报 */
	public void addEnd(EndReport report) {
		if (ends == null) {
			ends = new ArrayList<EndReport>();
		}
		ends.add(report);
	}

	/** 添加回合开始战报 */
	public void addStart(StartReport report) {
		if (starts == null) {
			starts = new ArrayList<StartReport>();
		}
		starts.add(report);
	}

	/** 添加战斗结果 */
	public void addBattleResult(BattleResult result) {
		this.battleResult = result;
	}

	/** 添加调试信息 */
	public void addDebug(DebugInfo debugInfo) {
		if (debugs == null) {
			debugs = new ArrayList<DebugInfo>();
		}
		debugs.add(debugInfo);
	}

	/** 添加战斗类型 */
	public void addBattleType(BattleType battleType) {
		this.battleType = battleType;
	}

	// Getter and Setter ...

	public int getRound() {
		return round;
	}

	protected void setRound(int round) {
		this.round = round;
	}

	public List<StartReport> getStarts() {
		return starts;
	}

	protected void setStarts(List<StartReport> starts) {
		this.starts = starts;
	}

	public List<UltimateReport> getUltimates() {
		return ultimates;
	}

	protected void setUltimates(List<UltimateReport> ultimates) {
		this.ultimates = ultimates;
	}

	public List<SkillReport> getSkills() {
		return skills;
	}

	protected void setSkills(List<SkillReport> skills) {
		this.skills = skills;
	}

	public FighterInfo getAttackerRelief() {
		return attackerRelief;
	}

	protected void setAttackerRelief(FighterInfo attackerRelief) {
		this.attackerRelief = attackerRelief;
	}

	public FighterInfo getDefenderRelief() {
		return defenderRelief;
	}

	protected void setDefenderRelief(FighterInfo defenderRelief) {
		this.defenderRelief = defenderRelief;
	}

	public List<EndReport> getEnds() {
		return ends;
	}

	protected void setEnds(List<EndReport> ends) {
		this.ends = ends;
	}

	public List<CdInfo> getCdInfos() {
		return cdInfos;
	}

	protected void setCdInfos(List<CdInfo> cdInfos) {
		this.cdInfos = cdInfos;
	}

	public BattleResult getBattleResult() {
		return battleResult;
	}

	protected void setBattleResult(BattleResult battleResult) {
		this.battleResult = battleResult;
	}

	public BattleType getBattleType() {
		return battleType;
	}

	public void setBattleType(BattleType battleType) {
		this.battleType = battleType;
	}

	public Map<Long, Object> getAdditions() {
		return additions;
	}

	protected void setAdditions(Map<Long, Object> additions) {
		this.additions = additions;
	}

	public List<DebugInfo> getDebugs() {
		return debugs;
	}

	protected void setDebugs(List<DebugInfo> debugs) {
		this.debugs = debugs;
	}

	// 构造方法

	/** 构造方法 */
	public static RoundReport valueOf(int round) {
		RoundReport result = new RoundReport();
		result.round = round;
		return result;
	}

}
