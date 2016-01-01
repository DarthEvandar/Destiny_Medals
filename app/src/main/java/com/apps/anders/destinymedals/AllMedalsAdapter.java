package com.apps.anders.destinymedals;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import java.util.ArrayList;

public class AllMedalsAdapter extends BaseAdapter {
    private Context mContext;
    //public Integer[] picIds;
    AllMedalsRunner async = new AllMedalsRunner();
    ArrayList<String> medals = async.getMedals();
    ArrayList<String> values = async.getValues();
    public AllMedalsAdapter(Context c/*,Integer[]ids*/) {
       //picIds=ids;
        mContext = c;
    }

    public int getCount() {
        return 102;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent){
        TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(250, 375));
            textView.setPadding(8, 8, 8, 8);
            textView.setTextSize(15);

        } else {
            textView = (TextView) convertView;
        }
        try{
        textView.setText(values.get(position));
        if("we_ran_out_of_medals".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.we_ran_out_of_medals,0,0);}
        else if("skewered".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.skewered,0,0);}
        else if("objectively_correct".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.objectively_correct,0,0);}
        else if("fallen_angel".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.fallen_angel,0,0);}
        else if("comeback".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.comeback,0,0);}
        else if("trial_by_fire".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.trial_by_fire,0,0);}
        else if("i_see_you".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.i_see_you,0,0);}
        else if("payback".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.payback,0,0);}
        else if("scouts_honor".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.scouts_honor,0,0);}
        else if("clean_sweep".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.clean_sweep,0,0);}
        else if("the_heist".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.the_heist,0,0);}
        else if("b_line".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.b_line,0,0);}
        else if("storm_bringer".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.storm_bringer,0,0);}
        else if("wild_hunt".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.wild_hunt,0,0);}
        else if("strength_of_the_wolf".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.strength_of_the_wolf,0,0);}
        else if("postmortem".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.postmortem,0,0);}
        else if("clear_a_path".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.clear_a_path,0,0);}
        else if("im_probe_able".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.im_probe_able,0,0);}
        else if("wont_be_beat".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.wont_be_beat,0,0);}
        else if("breaker".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.breaker,0,0);}
        else if("finger_on_the_pulse".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.finger_on_the_pulse,0,0);}
        else if("ace".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ace,0,0);}
        else if("mark_of_the_unbroken".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.mark_of_the_unbroken,0,0);}
        else if("hazard_pay".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.hazard_pay,0,0);}
        else if("the_cycle".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.the_cycle,0,0);}
        else if("at_any_cost".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.at_any_cost,0,0);}
        else if("denied".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.denied,0,0);}
        else if("zero_hour".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.zero_hour,0,0);}
        else if("relic_hunter".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.relic_hunter,0,0);}
        else if("automatic".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.automatic,0,0);}
        else if("merciless".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.merciless,0,0);}
        else if("heartbreaker".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.heartbreaker,0,0);}
        else if("narrow_escape".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.narrow_escape,0,0);}
        else if("armed_and_dangerous".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.armed_and_dangerous,0,0);}
        else if("sum_of_all_tears".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.sum_of_all_tears,0,0);}
        else if("master_blaster".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.master_blaster,0,0);}
        else if("medic".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.medic,0,0);}
        else if("immovable_object".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.immovable_object,0,0);}
        else if("bullseye".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bullseye,0,0);}
        else if("from_the_brink".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.from_the_brink,0,0);}
        else if("get_it_off".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.get_it_off,0,0);}
        else if("gutted".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.gutted,0,0);}
        else if("never_speak_of_this_again".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.never_speak_of_this_again,0,0);}
        else if("defender".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.defender,0,0);}
        else if("clutch".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.clutch,0,0);}
        else if("end_of_the_line".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.end_of_the_line,0,0);}
        else if("never_say_die".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.never_say_die,0,0);}
        else if("way_of_the_gun".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.way_of_the_gun,0,0);}
        else if("sidekick".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.sidekick,0,0);}
        else if("back_in_action".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.back_in_action,0,0);}
        else if("annihilation".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.annihilation,0,0);}
        else if("relentless".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.relentless,0,0);}
        else if("decisive_victory".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.decisive_victory,0,0);}
        else if("hammer_and_tongs".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.hammer_and_tongs,0,0);}
        else if("lone_wolf".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.lone_wolf,0,0);}
        else if("angel_of_light".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.angel_of_light,0,0);}
        else if("chariot_of_fire".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.chariot_of_fire,0,0);}
        else if("on_the_bright_side...".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.on_the_bright_side,0,0);}
        else if("slayer".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.slayer,0,0);}
        else if("splash_damage".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.splash_damage,0,0);}
        else if("overwatch".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.overwatch,0,0);}
        else if("bulldozer".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bulldozer,0,0);}
        else if("unstoppable_force".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.unstoppable_force,0,0);}
        else if("first_blood".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.first_blood,0,0);}
        else if("shutout".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.shutout,0,0);}
        else if("scorched_earth".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.scorched_earth,0,0);}
        else if("machine_lord".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.machine_lord,0,0);}
        else if("reign_of_terror".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.reign_of_terror,0,0);}
        else if("sword_at_a_gun_fight".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.sword_at_a_gun_fight,0,0);}
        else if("reaper".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.reaper,0,0);}
        else if("triple_down".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.triple_down,0,0);}
        else if("unsung_hero".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.unsung_hero,0,0);}
        else if("no_mercy".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.no_mercy,0,0);}
        else if("double_down".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.double_down,0,0);}
        else if("disruption".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.disruption,0,0);}
        else if("gunner".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.gunner,0,0);}
        else if("cry_havoc".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.cry_havoc,0,0);}
        else if("blast_shield".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.blast_shield,0,0);}
        else if("marksman".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.marksman,0,0);}
        else if("perfect_runner".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.perfect_runner,0,0);}
        else if("wrecking_ball".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.wrecking_ball,0,0);}
        else if("enemy_of_my_enemy".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.enemy_of_my_enemy,0,0);}
        else if("phantom".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.phantom,0,0);}
        else if("...and_stay_down".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.and_stay_down,0,0);}
        else if("nail_in_the_coffin".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.nail_in_the_coffin,0,0);}
        else if("avenger".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.avenger,0,0);}
        else if("salvage_crew".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.salvage_crew,0,0);}
        else if("dead_mans_hand".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.dead_mans_hand,0,0);}
        else if("the_best…around".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.the_bestaround,0,0);}
        else if("space_magic".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.space_magic,0,0);}
        else if("hat_trick".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.hat_trick,0,0);}
        else if("saboteur".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.saboteur,0,0);}
        else if("lockdown".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.lockdown,0,0);}
        else if("bulletproof".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bulletproof,0,0);}
        else if("buckshot_bruiser".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.buckshot_bruiser,0,0);}
        else if("domination".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.domination,0,0);}
        else if("alone_at_the_top".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.alone_at_the_top,0,0);}
        else if("stick_around".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.stick_around,0,0);}
        else if("seventh_column".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seventh_column,0,0);}
        else if("enforcer".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.enforcer,0,0);}
        else if("uprising".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.uprising,0,0);}
        else if("victory".equals(medals.get(position))){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.victory,0,0);}
        }catch(java.lang.IndexOutOfBoundsException exc){

        }
        return textView;
    }

    // references to our images



}