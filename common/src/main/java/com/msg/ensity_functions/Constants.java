/*
En-sityFunctions
Copyright (C) 2025 - MikeStorm03

This Source Code Form is subject to the terms of the Mozilla Public
License, v. 2.0. If a copy of the MPL was not distributed with this
file, You can obtain one at https://mozilla.org/MPL/2.0/.
*/
package com.msg.ensity_functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.resources.Identifier;

public interface  Constants {

	String NAMESPACE = "msg";
	String ID = "ensity_functions";
	String NAME = "En-sityFunctions";
	Logger LOG = LoggerFactory.getLogger(NAME);

	static Identifier identifier(String name){
		return Identifier.fromNamespaceAndPath(Constants.NAMESPACE, name);
	}
}