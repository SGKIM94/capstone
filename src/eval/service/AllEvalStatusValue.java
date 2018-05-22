package eval.service;

import java.util.Calendar;

public class AllEvalStatusValue {
	/*평가 계획서 관련 */
	private final static int DEFAULT_EVAL_PLAN_STATE = 0;
	private final static int EVAL_PLAN_STARTED = 1;
	private final static int EVAL_PLAN_ENDED = 2;
	private final static String EVAL_PLAN_DOCU_NO = "-01";
	
	/* 개별 평가서 관련 */
	private final static int DEFAULT_EPAPER_STATE = 0;
	private final static int EPAPER_EVAL_STARTED = 1;
	private final static int EPAPER_EVAL_ENDED = 2;
	
	/* 합격, 재심사, 불합격 기준 */
	/* 이 값을 초과해야함 */
	private final static int PASS = 44;
	private final static int RE_TEST = 34;
	
	/* 최종 평가서 문서 번호 디폴트 값 */
	final private static String DEFAULT_FINAL = "ff";
	
	/* 평가 문항 숫자 = 7 */
	final private static int DEFAULT_QUESTION_NO = 7;
	
	/* 평가시 문항에 대한 의견 디폴트 값 null */
	private final static String DEFAULT_COMMENT = null;
	
	/* 점수에 관한 디폴트 값*/
	private final static int DEFAULT_SCORE = 0;
	private final static int DEFAULT_TOTAL = 0;
	private final static double DEFAULT_AVG = 0.0;
	
	//private final int FAIL = 0;			굳이 있을 필요가 없음.
	public static int getDefaultEvalPlanState() {
		return DEFAULT_EVAL_PLAN_STATE;
	}
	public static int getEvalPlanStarted() {
		return EVAL_PLAN_STARTED;
	}
	public static int getEvalPlanEnded() {
		return EVAL_PLAN_ENDED;
	}
	public static int getDefaultEpaperState() {
		return DEFAULT_EPAPER_STATE;
	}
	public static int getEpaperEvalStarted() {
		return EPAPER_EVAL_STARTED;
	}
	public static int getEpaperEvalEnded() {
		return EPAPER_EVAL_ENDED;
	}
	public static int getPass() {
		return PASS;
	}
	public static int getReTest() {
		return RE_TEST;
	}
	public static String getDefaultFinal() {
		return DEFAULT_FINAL;
	}
	public static int getDefaultQuestionNo() {
		return DEFAULT_QUESTION_NO;
	}
	public static String getDefaultComment() {
		return DEFAULT_COMMENT;
	}
	public static int getDefaultScore() {
		return DEFAULT_SCORE;
	}
	public static int getDefaultTotal() {
		return DEFAULT_TOTAL;
	}
	public static double getDefaultAvg() {
		return DEFAULT_AVG;
	}
	public static String getEvalPlanDocuNo() {
		return EVAL_PLAN_DOCU_NO;
	}
	
	public static String togetStrYear() {
		Calendar currentCalendar = Calendar.getInstance();
		return Integer.toString(currentCalendar.get(Calendar.YEAR));
	}
	
}
