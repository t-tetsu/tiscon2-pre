SELECT campaign_id, title, statement, goal, create_user_id, first_name, last_name
FROM campaign, user
WHERE campaign.create_user_id = user.user_id AND campaign_id=/*campaignId*/1;
