package com.undeadstudio.gdungeon.ashley;

import com.badlogic.ashley.core.Component;


public class Position extends Component {
  public float x, y;

  public Position(float x, float y, float dir) {
    this.x = x;
    this.y = y;
  }
}