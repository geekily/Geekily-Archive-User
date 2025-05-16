class Signup{
	constructor(){
		this.obj = {
			email 			: {$ : $('#email')}
			, code 			: {$ : $('#code')}
			, codeTimer		: {$ : $('#codeTimer'), interval : null}
			, password		: {$ : $('#password')}
			, passwordCheck	: {$ : $('#passwordCheck')}
			, userName		: {$ : $('#userName')}
			, archiveName	: {$ : $('#archiveName')}
			, btn 	: {
				sendCode 		: {$ : $('#btn-send-code')}
				, resendCode 	: {$ : $('#btn-resend-code')}
				, check 		: {$ : $('#btn-check')}
				, save 			: {$ : $('#btn-save')}
			}
		}
		this.eventHandlers = {
			validateEmail 		: this.fnValidateEmail
			, sendCode			: this.fnSendCode
			, timerStart		: this.fnTimerStart
			, checkCode			: this.fnCheckCode
			, save				: this.fnSave
		}
		this.message = {
			email : {
				fill : null
				, format : null
			}
			, code : {
				fill	: null
				, sending : null 
				, wrong : null
			}
			, password : {
				fill : null
				, min : null
				, format : null
				, match : null
			}
			, username : {
				fill : null
			}
			, archive : {
				fill : null
				, min : null
			}
			, common : {
				saving : null
			}
		}
		this.init();
	}
	
	init(){
		this.addEventListeners();
	}
	
	addEventListeners(){
		this.obj.btn.sendCode.$.on('click', this.eventHandlers.validateEmail.bind(this));
		this.obj.btn.resendCode.$.on('click', () => this.eventHandlers.sendCode.bind(this)(this.obj.email.value));
		this.obj.btn.check.$.on('click', this.eventHandlers.checkCode.bind(this));
		this.obj.btn.save.$.on('click', this.eventHandlers.save.bind(this));
	}
	
	/* function : validate email */
	async fnValidateEmail(){
		let email = $.trim(this.obj.email.$.val());
    	if(isEmpty(email)){
    		openModal({type : 'alert', message : this.message.email.fill})
			return
    	}
    	
    	if(!isEmailFormat(email)){
			openModal({type : 'alert', message : this.message.email.format})
			return
    	}
    
    	let response = await $.ajax({
			type			: 'post'
			, url			: getFullPath('/sign/checkEmailExistence')
			, contentType	: 'application/json'
			, data			: JSON.stringify({email : email})
        })
        
    	if(response.resultCode == 1){
    		this.obj.email.$.prop('readonly', true);
    		this.obj.code.$.prop('readonly', false);
    		this.obj.btn.sendCode.$.addClass('hide');
    		this.obj.btn.resendCode.$.removeClass('hide').prop('disabled', false);
    		this.obj.btn.check.$.prop('disabled', false);
    		this.obj.email.$.on('input', () => goToPage('/'));
    	}else{
    		openModal({type: 'alert', message : response.resultMessage});
    		return
    	}
  		
    	this.obj.email.value = email;
    	this.eventHandlers.sendCode.call(this, email);
	}
	
	/* functoin : send code to email */
	fnSendCode = async (email) => {
		let response = await $.ajax({
            type			: 'post'
            , url			: getFullPath('/email')
            , contentType	: 'application/json'
            , data			: JSON.stringify({purpose : 'signup', email : email})
            , beforeSend	: () => {
            	openLoading(this.message.code.sending);
            }
        });
    	
    	if(response.resultCode == 1){
    		this.obj.code.value = response.code;
    		this.eventHandlers.timerStart.call(this);
    	}
    	
    	openModal({
			type 		: 'alert'
			, message	: response.resultMessage
			, close		: () => {
				closeLoading();
				this.obj.code.$.focus();
			}
		})
		
		this.obj.btn.resendCode.$.prop('disabled', true);
		setTimeout(() => {
			this.obj.btn.resendCode.$.prop('disabled', false);
		}, 10000)
	}
	
	/* function : start code timer */
	async fnTimerStart(){
		clearInterval(this.obj.codeTimer.interval);
    	
    	this.obj.codeTimer.$.text('3:00');
    	this.obj.codeTimer.interval = setInterval(() => {
    		let time = this.obj.codeTimer.$.text().split(':');
    		let min = Number(time[0]);
    		let sec = Number(time[1]);
    		
    		if(sec == 0){
    			sec = 59;
    			min--;
    		}else{
    			sec--;
    		}
    		
    		if(min == 0 && sec == 0){
    			clearInterval(this.obj.codeTimer.interval);
    			this.obj.btn.check.$.prop('disabled', true);
    		}
    		
    		this.obj.codeTimer.$.text(min + ':' + (sec < 10 ? '0' + sec : sec));
    	}, 1000)
	}
	
	/* function : check code */
	fnCheckCode(){
		if(isEmpty(this.obj.code.$.val())){
			openModal({type : 'alert', message : this.message.code.fill})
			return
		}
		if(this.obj.code.value != this.obj.code.$.val()){
			openModal({type : 'alert', message : this.message.code.wrong})
			return
		}
		this.obj.code.$.prop('readonly', true);
		this.obj.codeTimer.$.remove();
		this.obj.btn.resendCode.$.prop('disabled', true);
		this.obj.btn.check.$.prop('disabled', true);
		this.obj.password.$.prop('readonly', false);
		this.obj.passwordCheck.$.prop('readonly', false);
		this.obj.userName.$.prop('readonly', false);
		this.obj.archiveName.$.prop('readonly', false);
		this.obj.btn.save.$.prop('disabled', false);
	}
    
    /* function : save */
    fnSave = async () => {
    	let password 		= $.trim(this.obj.password.$.val());
    	let passwordCheck 	= $.trim(this.obj.passwordCheck.$.val());
    	let userName 		= $.trim(this.obj.userName.$.val());
    	let archiveName 	= $.trim(this.obj.archiveName.$.val());
    	if(isEmpty(password)){
    		openModal({type : 'alert', message : this.message.password.fill});
	    	return
    	}
    	if(password.length < 10){
    		openModal({type : 'alert', message : this.message.password.min});
	    	return
    	}
    	if(!isPasswordFormat(password)){
	    	openModal({type : 'alert', message : this.message.password.format});
	    	return
    	}
	    if(password != passwordCheck){
	    	openModal({type : 'alert', message : this.message.password.match});
	    	return
	    }
	    if(isEmpty(userName)){
		    openModal({type : 'alert', message : this.message.username.fill});
	    	return
	    }
	    if(isEmpty(archiveName)){
		    openModal({type : 'alert', message : this.message.archive.fill});
		    return
	    }
	    if(archiveName.length < 3){
		    openModal({type : 'alert', message : this.message.archive.min});
		    return
	    }
	    
	    let checkResponse = await $.ajax({
			type			: 'post'
			, url			: getFullPath('/sign/checkArchiveNameExistence')
			, contentType	: 'application/json'
			, data			: JSON.stringify({archiveName : archiveName})
        })
        
        if(checkResponse.resultCode == 1){
    		this.obj.btn.save.$.prop('disabled', true);
    	}else{
    		openModal({type: 'alert', message : checkResponse.resultMessage});
    		return
    	}
	    
	    let data = {
		    email			: this.obj.email.$.val()
		    , password		: this.obj.password.$.val()
		    , userName		: this.obj.userName.$.val()
		    , archiveName	: this.obj.archiveName.$.val()
	    }

	    let signupResponse = await $.ajax({
            type			: 'post'
            , url			: getFullPath('/sign/signup')
            , contentType	: 'application/json'
            , data			: JSON.stringify(data)
            , beforeSend	: () => {
            	openLoading(this.message.common.saving);
            }
        });
	    
	    let modalParam = {
            type 		: 'alert'
            , message 	: signupResponse.resultMessage
            , close 	: () => {
            	closeLoading();
            	if(signupResponse.resultCode == 1){
	            	goToPage('/sign/signin')
            	}else{
            		this.obj.btn.save.$.prop('disabled', false);
            	}
            }
        }
        openModal(modalParam);
    }
}

class Signin{
	constructor(){
		this.obj = {
			email 			: {$ : $('#email')}
			, password		: {$ : $('#password')}
			, btn 	: {
				save : {$ : $('#btn-signin')}
			}
		}
		this.eventHandlers = {
			save : this.fnSave
		}
		this.message = {
			email : {
				fill : null
				, format : null
			}
			, password : {
				fill : null
			}
		}
		this.init();
	}
	
	init(){
		this.addEventListeners();
	}
	
	addEventListeners(){
		this.obj.password.$.on('keydown', (e) => {
					if(e.keyCode === 13){
						this.eventHandlers.save.bind(this)();
					}
				});
		this.obj.btn.save.$.on('click', this.eventHandlers.save.bind(this));
	}
	
    /* function : save */
    async fnSave(){
    	let email 		= $.trim(this.obj.email.$.val());
    	let password 	= $.trim(this.obj.password.$.val());
    	
    	if(isEmpty(email)){
	    	openModal({type : 'alert', message : this.message.email.fill});
	    	return
    	}
    	
    	if(!isEmailFormat(email)){
	    	openModal({type : 'alert', message : this.message.email.format});
	    	return
    	}
    	
    	if(isEmpty(password)){
    		openModal({type : 'alert', message : this.message.password.fill});
	    	return
    	}
	    
    	this.obj.btn.save.$.prop('disabled', true);
    	let data = {
    		email 		: email
    		, password 	: password
    	}
	    let signinResponse = await $.ajax({
			type			: 'post'
			, url			: getFullPath('/sign/signin')
			, contentType	: 'application/json'
			, data			: JSON.stringify(data)
        })
        
        if(signinResponse.resultCode == 1){
    		goToPage('/');
    	}else{
    		openModal({
    			type		: 'alert'
    			, message 	: signinResponse.resultMessage
    			, close 	: () => {
    				this.obj.btn.save.$.prop('disabled', false);
    			}
    		});
    	}
    }
}