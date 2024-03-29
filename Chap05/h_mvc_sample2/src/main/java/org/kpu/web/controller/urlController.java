package org.kpu.web.controller;

import org.kpu.web.domain.StudentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class urlController {
	
	private static final Logger logger = LoggerFactory.getLogger(urlController.class);
	
		// http://localhost:8080/web/try/thankyou
		@RequestMapping(value="/try/{msg}", method = RequestMethod.GET)
		public String getUserTest( @PathVariable("msg")  String msg) {
		               	logger.info(msg);  
			logger.info(" /try URL called. then getUserTest method executed.");
			return "result_a";
		}
	
		// http://localhost:8080/web/tryA?msg=thankyou
		@RequestMapping(value="/tryA", method=RequestMethod.GET)
		public String getUserTest1(@RequestParam("msg") String msg){
			logger.info(msg);
			logger.info("/tryA URL called. then getUserTest1 method executed.");
			return "result_a";
		}
		
		//http://localhost:8080/web/tryB?msg=thankyou
		@RequestMapping(value="/tryB", method=RequestMethod.GET)
		public String getUserTest2(@ModelAttribute("msg")String msg) {
			logger.info(msg);
			logger.info("/tryB URL called. then getUserTest2 method executed");
			return "result_b";
		}
		
		//http://localhost:8080/web/tryC?msg=thankyou
		@RequestMapping(value={"/tryC", "/tryD"}, method=RequestMethod.GET)
		public String getUsetTest2(@ModelAttribute("msg") String msg) {
			logger.info(msg);
			logger.info("/tryC, D URL called. then getUserTest2 method executed.");
			return "result_c";
		}
		
		//http://localhost:8080/web/tryF
		@RequestMapping(value={"/tryF"}, method=RequestMethod.GET)
		public String getUsetTest4Get() {
			logger.info("/tryF URL GET method called. then getUserTest4 method executed.");
			return "register";
		}
		
		//http://localhost:8080/web/tryF
		@RequestMapping(value={"/tryF"}, method=RequestMethod.POST)
		public String getUsetTest5Post(@ModelAttribute("student")StudentVO vo) {
			logger.info(vo.toString());
			logger.info("/tryF URL POST method called. then getUserTest5 method executed.");
			return "result_info";
		}
		
		//http://localhost:8080/web/tryFwd?msg=thankyou
		@RequestMapping(value={"/tryFwd"}, method=RequestMethod.GET)
		public String getUsetTest6(@ModelAttribute("msg")String msg) {
			logger.info(msg);
			logger.info("/tryFwd URL GET method called. then getUserTest6 method executed.");
			return "forward:/tryB";
		}
		
		//http://localhost:8080/web/tryRdt?msg=thankyou
		@RequestMapping(value={"/tryRdt"}, method=RequestMethod.GET)
		public String getUsetTest7(@ModelAttribute("msg")String msg) {
			logger.info(msg);
			logger.info("/tryFwd URL GET method called. then getUserTest7 method executed.");
			return "redirect:/tryB";
		}
}
