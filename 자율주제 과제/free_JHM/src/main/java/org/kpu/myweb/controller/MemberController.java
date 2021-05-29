package org.kpu.myweb.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kpu.myweb.domain.DateData;
import org.kpu.myweb.domain.ReservVO;
import org.kpu.myweb.domain.StudentVO;
import org.kpu.myweb.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	@Autowired
    private MemberService memberService;
	private ReservVO res;
	private static ApplicationContext ctx = null;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@RequestMapping(value = "calendar", method = RequestMethod.GET)
	public String calendar(Model model, HttpServletRequest request, DateData dateData){
		
		Calendar cal = Calendar.getInstance();
		DateData calendarData;
		//검색 날짜
		if(dateData.getDate().equals("")&&dateData.getMonth().equals("")){
			dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)),String.valueOf(cal.get(Calendar.MONTH)),String.valueOf(cal.get(Calendar.DATE)),null);
		}
		//검색 날짜 end

		Map<String, Integer> today_info =  dateData.today_info(dateData);
		List<DateData> dateList = new ArrayList<DateData>();
		
		//실질적인 달력 데이터 리스트에 데이터 삽입 시작.
		//일단 시작 인덱스까지 아무것도 없는 데이터 삽입
		for(int i=1; i<today_info.get("start"); i++){
			calendarData= new DateData(null, null, null, null);
			dateList.add(calendarData);
		}
		
		//날짜 삽입
		for (int i = today_info.get("startDay"); i <= today_info.get("endDay"); i++) {
			if(i==today_info.get("today")){
				calendarData= new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()), String.valueOf(i), "today");
			}else{
				calendarData= new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()), String.valueOf(i), "normal_date");
			}
			dateList.add(calendarData);
		}

		//달력 빈곳 빈 데이터로 삽입
		int index = 7-dateList.size()%7;
		
		if(dateList.size()%7!=0){
			
			for (int i = 0; i < index; i++) {
				calendarData= new DateData(null, null, null, null);
				dateList.add(calendarData);
			}
		}
		System.out.println(dateList);
		
		//배열에 담음
		model.addAttribute("dateList", dateList);		//날짜 데이터 배열
		model.addAttribute("today_info", today_info);
		return "member/calendar";
	}
	
	@RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listMember(Model model) throws Exception {
    	List<StudentVO> students = memberService.readMemberList();
    	
		logger.info(" /member/list URL called. then listMemebr method executed.");
        model.addAttribute("students", students);
        return "member/member_list";
    }
	
//	@RequestMapping(value = {"/reservation"}, method = RequestMethod.GET)
//    public String listReserv(Model model) throws Exception {
//    	List<ReservVO> reserv = memberService.readReservList();
//    	
//		logger.info(" reservation/reserv_list URL called. then listMemebr method executed.");
//        model.addAttribute("reserv", reserv);
//        return "member/reservation";
//    }
    
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String readMember(@RequestParam("id") String id, Model model) throws Exception {
    	StudentVO student = memberService.readMember(id);
    	
		logger.info(" /read?id=kang URL called. then readMemebr method executed.");
        model.addAttribute("student", student);
        return "member/member_read";
    }
    
	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public String createMemberGet() throws Exception {
		logger.info(" /register URL GET method called. then forward member_register.jsp.");
		return "member/join";
	}
	
    
    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
	public String createMemberPost( @ModelAttribute("student") StudentVO vo) throws Exception {
		memberService.addMember(vo);
		logger.info(vo.toString());
		logger.info(" /register URL POST method called. then createMember method executed.");
		return "redirect:/";
	}

	@RequestMapping(value = {"/reservation"}, method = RequestMethod.GET)
	public String reservationGet() throws Exception {
		logger.info("/register URL GET method called. then forward member_register.jsp.");
		return "member/reservation";
	}
	
    @RequestMapping(value = {"/reservation"}, method = RequestMethod.POST)
	public String reservationPost( @ModelAttribute("student") ReservVO vo) throws Exception {
		memberService.addReservation(vo);
		logger.info(vo.toString());
		logger.info(" /register URL POST method called. then createMember method executed.");
		return "redirect:/";
	}
    
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String loginMemberGet() throws Exception {
		logger.info(" /register URL GET method called. then forward member_register.jsp.");
		return "member/login";
	}
	
	@RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
	public String logoutMemberGet() throws Exception {
		logger.info(" /register URL GET method called. then forward member_register.jsp.");
		return "member/logoutAction";
	}
    
	
    @PostMapping(path="/login")
    public String login(@RequestParam(name="userPassword", required=true) String passwd,
			HttpSession session, HttpServletRequest request,
			RedirectAttributes redirectAttr){
    	String id = request.getParameter("userID");
		session.setAttribute("member", id);
		
		return "redirect:/";
	}
    
    @RequestMapping(value = "/myres", method = RequestMethod.GET)
    public String readMyreservation(Model model, HttpSession session) throws Exception {
    	String id = (String)session.getAttribute("member");
		logger.info("session = "+ id);
    	ReservVO res = memberService.readMyReserv(id);

        model.addAttribute("myres", res);
        return "member/myres";
    }

            
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    public String modifyMemberGet(@RequestParam("id") String id, Model model) throws Exception {
    	ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
    	memberService = ctx.getBean(MemberService.class);
    	ReservVO student = memberService.readMyReserv(id);
        model.addAttribute("student", student);
        return "member/member_modify";
    }
    
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modifyMemberPost(@ModelAttribute("student") ReservVO vo) throws Exception {
    	ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
    	memberService = ctx.getBean(MemberService.class);
    	ReservVO student = vo;
		logger.info("modify VO = " + vo.toString());
    	memberService.updateMember(vo);
		logger.info(" /modify?id=kang URL POST method called. then modifyMemberPost method executed.");
        return "redirect:/member/myres";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteMemberGet(@RequestParam("id") String id, Model model) throws Exception {
    	memberService.deleteMember(id);
    	
		logger.info(" /delete URL GET method called. then forward member_modify.jsp.");
        return "redirect:/member/myres";
    }
/*  MemberControllerAdvice에 예외처리 기능적용    
    @ExceptionHandler(DataNotFoundException.class)
    public String handleException(DataNotFoundException e) {
        return "member/not_found";
    }
*/
   
}