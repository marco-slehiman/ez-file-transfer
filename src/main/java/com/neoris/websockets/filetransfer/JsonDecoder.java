package com.neoris.websockets.filetransfer;

import javax.websocket.DecodeException;
import javax.websocket.Decoder.Text;
import javax.websocket.EndpointConfig;


import com.google.gson.Gson;

// TODO: Auto-generated Javadoc
/**
 * The Class JsonDecoder.
 */
public class JsonDecoder implements Text<Operation> {

	/* (non-Javadoc)
	 * @see javax.websocket.Decoder#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.websocket.Decoder#init(javax.websocket.EndpointConfig)
	 */
	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.websocket.Decoder.Text#decode(java.lang.String)
	 */
	@Override
	public Operation decode(String msg) throws DecodeException {
		Gson gson = new Gson();
		Operation result  = gson.fromJson(msg, Operation.class);
		return result;
	}

	/* (non-Javadoc)
	 * @see javax.websocket.Decoder.Text#willDecode(java.lang.String)
	 */
	@Override
	public boolean willDecode(String msg) {
		if (msg.contains("op")){
			return true;
		}else{
			return false;
		}
	}

}
