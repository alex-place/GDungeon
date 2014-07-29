/*******************************************************************************
 * Copyright 2014 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.undeadstudio.gdungeon.ashley.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;
import com.undeadstudio.gdungeon.ashley.components.MovementComponent;
import com.undeadstudio.gdungeon.ashley.components.PositionComponent;
import com.undeadstudio.gdungeon.ashley.utils.Timer;

public class MovementSystem extends IteratingSystem {
	PositionComponent position;
	MovementComponent movement;
	float speed = 0.2f;
	Timer timer = new Timer();

	public MovementSystem() {
		super(Family.getFamilyFor(PositionComponent.class,
				MovementComponent.class));
	}

	@Override
	public void processEntity(Entity entity, float deltaTime) {
		position = entity.getComponent(PositionComponent.class);
		movement = entity.getComponent(MovementComponent.class);
		if (timer.contains("time")) {
			if (timer.getTime("time") > 500) {
				movement.velocityX += MathUtils.random(-speed, speed);
				movement.velocityY += MathUtils.random(-speed, speed);
			}
		} else {
			timer.start("time");
		}

		position.x += movement.velocityX * deltaTime;
		position.y += movement.velocityY * deltaTime;

	}
}
