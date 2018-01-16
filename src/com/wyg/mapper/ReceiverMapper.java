package com.wyg.mapper;

import com.wyg.pojo.Receiver;

public interface ReceiverMapper {
	void insertReceiver(Receiver receiver);
	Receiver findReceiverByUserId(long id);
}
