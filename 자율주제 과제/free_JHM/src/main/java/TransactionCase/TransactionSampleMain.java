package TransactionCase;


import org.kpu.myweb.domain.ReservVO;
import org.kpu.myweb.service.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TransactionSampleMain {
	
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("안녕하세요 TRANSACTION-MYBATIS");
	
		ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//		ctx = new ClassPathXmlApplicationContext("classpath:applicationContext_annotation.xml");
		
		MemberService memberService = ctx.getBean(MemberService.class);  // by Class name
		
		String strID1 = "Test3";
		ReservVO vo1 = new ReservVO(); 
		vo1.setResid(strID1); vo1.setResname(strID1);  vo1.setResdate(strID1); vo1.setResuseremail(strID1); vo1.setResusermobile(strID1);
		vo1.setResusername(strID1);
		
		String strID2 = "Test3";
		ReservVO vo2 = new ReservVO();
		vo2.setResid(strID2); vo2.setResname(strID2);  vo2.setResdate(strID2); vo2.setResuseremail(strID2); vo2.setResusermobile(strID2);
		vo2.setResusername(strID2);
		
		try {
			memberService.updateMemberList1(vo1);
			System.out.println("Test1 처리완료");
		} catch(Exception e) {
			System.out.println(e);
		}
		
		
		try {
			memberService.updateMemberList1(vo1);
			System.out.println("Test2 처리 완료");
		} catch(Exception e) {
			System.out.println(e);
		}
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					memberService.updateMemberList1(vo1);
//					System.out.println("TRANSACTION 처리 완료");
//				} catch(Exception e) {
//					System.out.println("실패");
//				}
//			}
//		}).start();;
//		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					memberService.updateMemberList1(vo2);
//					System.out.println("TRANSACTION 처리 완료");
//				} catch(Exception e) {
//					System.out.println("실패");
//				}
//			}
//		}).start();;
		
	}
}
